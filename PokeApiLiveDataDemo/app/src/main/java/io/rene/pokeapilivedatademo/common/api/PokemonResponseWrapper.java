package io.rene.pokeapilivedatademo.common.api;

import java.util.ArrayList;

import io.rene.pokeapilivedatademo.common.models.Pokemon;

public class PokemonResponseWrapper {

    public ArrayList<Pokemon> pokemons = new ArrayList<>();

    public PokemonResponseWrapper(ArrayList<Pokemon> pokemons) {
        this.pokemons.addAll(pokemons);
        for (int i = 0;i< pokemons.size();i++){
            Pokemon pokemon = pokemons.get(i);
            pokemon.setUrl(pokemon.getUrl());
        }
    }




}
