package io.rene.pokeapilivedatademo.listpokemons;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import io.rene.pokeapilivedatademo.common.api.PokemonApi;
import io.rene.pokeapilivedatademo.common.api.PokemonRepository;
import io.rene.pokeapilivedatademo.common.api.PokemonV2Service;
import io.rene.pokeapilivedatademo.common.db.AppDatabase;
import io.rene.pokeapilivedatademo.common.db.PokemonCache;
import io.rene.pokeapilivedatademo.common.models.Pokemon;

public class ListPokemonViewModel extends ViewModel{

    public PokemonRepository repository;

    public MutableLiveData<Boolean> mStartLiveData = new MutableLiveData<>();

    public LiveData<PagedList<Pokemon>> mPokemonsLiveData = Transformations.switchMap(mStartLiveData, new Function<Boolean, LiveData<PagedList<Pokemon>>>() {
        @Override
        public LiveData<PagedList<Pokemon>> apply(Boolean start) {
            return repository.pokemons();
        }
    });
}
