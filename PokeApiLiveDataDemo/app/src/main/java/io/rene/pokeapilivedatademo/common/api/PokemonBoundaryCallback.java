package io.rene.pokeapilivedatademo.common.api;

import android.arch.paging.PagedList;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import io.rene.pokeapilivedatademo.common.db.PokemonCache;
import io.rene.pokeapilivedatademo.common.models.Pokemon;
import io.rene.pokeapilivedatademo.common.models.PokemonResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonBoundaryCallback extends PagedList.BoundaryCallback<Pokemon> {

    private static final int  NETWORK_PER_PAGE = 20;
    private PokemonCache cache;
    private PokemonV2Service service;

    private Integer page = 0;

    private static final Object LOCK = new Object();
    private AtomicBoolean noData = new AtomicBoolean(false);

    private AtomicBoolean isRequestInProgress = new AtomicBoolean(false);

    public void setPage(Integer page) {
        synchronized (LOCK){
            this.page = page;
        }
    }

    public Integer getPage() {
        synchronized (LOCK){
            return page;
        }
    }

    public PokemonBoundaryCallback(PokemonCache cache, PokemonV2Service service) {
        this.cache = cache;
        this.service = service;
    }

    private synchronized int offset(){
        int currentPage = getPage();
        int offset = currentPage * NETWORK_PER_PAGE;
        if (!noData.get()){
            setPage(currentPage + 1);
        }
        return offset;
    }

    private void requestAndSaveData() {
        if (isRequestInProgress.get()){
            return;
        }

        isRequestInProgress.set(true);

        service.pokemons(offset()).enqueue(new Callback<PokemonResult>() {
            @Override
            public void onResponse(@NonNull Call<PokemonResult> call, @NonNull Response<PokemonResult> response) {
                isRequestInProgress.set(false);

                if (response.body().getNext() == null){
                    noData.set(true);
                }
                if (response.body().getCount() > 0){
                    cache.insert(response.body().getPokemonResponse(), new PokemonCache.InsertionCallback() {
                        @Override
                        public void onInsertionCompleted() {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<PokemonResult> call, Throwable t) {
                isRequestInProgress.set(false);
            }
        });
    }

    @Override
    public void onZeroItemsLoaded() {
        requestAndSaveData();
    }

    @Override
    public void onItemAtEndLoaded(@NonNull Pokemon itemAtEnd) {
        requestAndSaveData();
    }
}
