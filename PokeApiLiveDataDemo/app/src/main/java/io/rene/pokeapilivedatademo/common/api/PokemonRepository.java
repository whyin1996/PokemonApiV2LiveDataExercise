package io.rene.pokeapilivedatademo.common.api;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import io.rene.pokeapilivedatademo.common.db.PokemonCache;
import io.rene.pokeapilivedatademo.common.models.Pokemon;

public class PokemonRepository {

    private PokemonV2Service service;
    private PokemonCache cache;

    private static final int DATABASE_PAGE_SIZE = 10;

    public PokemonRepository(PokemonV2Service service, PokemonCache cache) {
        this.service = service;
        this.cache = cache;
    }

    @SuppressWarnings("unchecked")
    public LiveData<PagedList<Pokemon>> pokemons(){
        DataSource.Factory<Integer, Pokemon> dataSourceFactory = cache.pokemons();
        PokemonBoundaryCallback boundaryCallback = new PokemonBoundaryCallback(cache, service);
        return new LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build();
    }

}
