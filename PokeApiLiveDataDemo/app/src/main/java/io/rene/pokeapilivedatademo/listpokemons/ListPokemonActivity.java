package io.rene.pokeapilivedatademo.listpokemons;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.rene.pokeapilivedatademo.R;
import io.rene.pokeapilivedatademo.listpokemons.utils.ActivityUtils;

public class ListPokemonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listpokemon);
        if (savedInstanceState == null){
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),ListPokemonFragment.create(),R.id.container);
        }
    }
}
