package io.rene.pokeapilivedatademo.listpokemons;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.rene.pokeapilivedatademo.R;
import io.rene.pokeapilivedatademo.common.api.PokemonApi;
import io.rene.pokeapilivedatademo.common.api.PokemonRepository;
import io.rene.pokeapilivedatademo.common.api.PokemonV2Service;
import io.rene.pokeapilivedatademo.common.db.AppDatabase;
import io.rene.pokeapilivedatademo.common.db.PokemonCache;
import io.rene.pokeapilivedatademo.common.models.Pokemon;
import io.rene.pokeapilivedatademo.common.models.PokemonResult;
import io.rene.pokeapilivedatademo.listpokemons.ui.PokemonAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPokemonFragment extends Fragment {

    public static ListPokemonFragment create(){
        return new ListPokemonFragment();
    }

    private RecyclerView mPokemonRecyclerView;
    private PokemonAdapter mPokemonAdapter;
    @LayoutRes
    public int mLayoutRes = R.layout.fragment_listpokemon;

    private PokemonV2Service  mService;
    private PokemonCache      mCache;
    private PokemonRepository mRepository;

    private ListPokemonViewModel mVm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(mLayoutRes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mService    = PokemonApi.get(PokemonV2Service.class);
        mCache      = new PokemonCache(AppDatabase.getInstance(getContext()).pokemonDao());
        mRepository = new PokemonRepository(mService, mCache);

        mVm = ViewModelProviders.of(this).get(ListPokemonViewModel.class);
        mVm.repository = mRepository;

        mPokemonRecyclerView = view.findViewById(R.id.pokemons);
        mPokemonAdapter = new PokemonAdapter();
        mPokemonRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mPokemonRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mPokemonRecyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        mPokemonRecyclerView.setAdapter(mPokemonAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mVm.mPokemonsLiveData.observe(this, new Observer<PagedList<Pokemon>>() {
            @Override
            public void onChanged(@Nullable PagedList<Pokemon> pokemons) {
                mPokemonAdapter.submitList(pokemons);
            }
        });
        mVm.mStartLiveData.postValue(true);
    }
}
