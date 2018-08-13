package io.rene.pokeapilivedatademo.common.api;

import android.arch.paging.PagedList;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import io.rene.pokeapilivedatademo.common.db.PokemonCache;
import io.rene.pokeapilivedatademo.common.helpers.PagingRequestHelper;
import io.rene.pokeapilivedatademo.common.models.Pokemon;
import io.rene.pokeapilivedatademo.common.models.PokemonResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonBoundaryCallback extends PagedList.BoundaryCallback<Pokemon> {

    private static final int NETWORK_PER_PAGE = 20;
    private PokemonCache cache;
    private PokemonV2Service service;
    private PagingRequestHelper mHelper = new PagingRequestHelper(Executors.newSingleThreadExecutor());
    private AtomicInteger page = new AtomicInteger(0);

    public PokemonBoundaryCallback(PokemonCache cache, PokemonV2Service service) {
        this.cache = cache;
        this.service = service;
    }

    private synchronized int offset(){
        return page.get() * NETWORK_PER_PAGE;
    }

    private void requestAndSaveData(final PagingRequestHelper.Request.Callback callback) {
        service.pokemons(offset()).enqueue(new Callback<PokemonResult>() {
            @Override
            public void onResponse(@NonNull Call<PokemonResult> call, @NonNull Response<PokemonResult> response) {

                ArrayList<Pokemon> pokemons = new PokemonResponseWrapper(response.body().getPokemonResponse()).pokemons;
                for (int i = 0;i< pokemons.size();i++){
                    Log.e("TTT","pokemon: " + pokemons.get(i).getName() + " id: " + pokemons.get(i).getId());
                }

                cache.insert(pokemons, new PokemonCache.InsertionCallback() {
                    @Override
                    public void onInsertionCompleted() {
                        page.incrementAndGet();
                        callback.recordSuccess();
                    }
                });
            }

            @Override
            public void onFailure(Call<PokemonResult> call, Throwable t) {
                callback.recordFailure(t);
            }
        });
    }

    @Override
    public void onZeroItemsLoaded() {
        mHelper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL, new PagingRequestHelper.Request() {
            @Override
            public void run(Callback callback) {
                requestAndSaveData(callback);
            }
        });

    }

    @Override
    public void onItemAtEndLoaded(@NonNull Pokemon itemAtEnd) {
        mHelper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER, new PagingRequestHelper.Request() {
            @Override
            public void run(Callback callback) {
                requestAndSaveData(callback);
            }
        });
    }
}
