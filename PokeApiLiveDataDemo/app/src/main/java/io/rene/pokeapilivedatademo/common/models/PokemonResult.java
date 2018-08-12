package io.rene.pokeapilivedatademo.common.models;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokemonResult {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("previous")
    @Expose
    private Object previous;
    @SerializedName("results")
    @Expose
    private ArrayList<Pokemon> pokemonRespons = null;
    @SerializedName("next")
    @Expose
    private String next;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public ArrayList<Pokemon> getPokemonResponse() {
        return pokemonRespons;
    }

    public void setPokemonRespons(ArrayList<Pokemon> pokemonRespons) {
        this.pokemonRespons = pokemonRespons;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public boolean hasPokemon(){
        return pokemonRespons != null && pokemonRespons.size() > 0;
    }

}
