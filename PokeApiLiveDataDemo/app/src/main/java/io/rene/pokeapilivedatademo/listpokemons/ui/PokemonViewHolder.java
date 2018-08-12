package io.rene.pokeapilivedatademo.listpokemons.ui;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.rene.pokeapilivedatademo.common.models.Pokemon;

public class PokemonViewHolder extends RecyclerView.ViewHolder{

    private TextView mPokemonNameField;

    @LayoutRes
    private static final int ITEM_LAYOUT_RES = android.R.layout.simple_list_item_1;

    private PokemonViewHolder(View itemView) {
        super(itemView);
        mPokemonNameField = itemView.findViewById(android.R.id.text1);
    }

    @SuppressWarnings("UnusedReturnValue")
    public PokemonViewHolder bind(Pokemon pokemon) {
        if (pokemon != null){
            this.mPokemonNameField.setText(pokemon.getName());
        }
        return this;
    }

    public static PokemonViewHolder create(@NonNull ViewGroup parent){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(ITEM_LAYOUT_RES, parent, false);
        return new PokemonViewHolder(itemView);
    }
}
