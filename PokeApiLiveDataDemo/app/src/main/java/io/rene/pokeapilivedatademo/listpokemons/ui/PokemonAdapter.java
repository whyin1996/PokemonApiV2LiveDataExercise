package io.rene.pokeapilivedatademo.listpokemons.ui;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.rene.pokeapilivedatademo.common.models.Pokemon;

public class PokemonAdapter extends PagedListAdapter<Pokemon, RecyclerView.ViewHolder>{

    public PokemonAdapter() {
        super(new DiffUtil.ItemCallback<Pokemon>() {
            @Override
            public boolean areItemsTheSame(Pokemon oldItem, Pokemon newItem) {
                return oldItem.getName().equals(newItem.getName());
            }

            @Override
            public boolean areContentsTheSame(Pokemon oldItem, Pokemon newItem) {
                return oldItem == newItem;
            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return PokemonViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, int position) {
        PokemonViewHolder holder = (PokemonViewHolder)h;
        if (getItem(position) != null){
            holder.bind(getItem(position));
        }
    }

}
