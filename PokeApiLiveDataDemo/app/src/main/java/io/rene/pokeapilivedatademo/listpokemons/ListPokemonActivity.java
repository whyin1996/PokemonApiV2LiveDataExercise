package io.rene.pokeapilivedatademo.listpokemons;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.idescout.sql.SqlScoutServer;

import io.rene.pokeapilivedatademo.R;
import io.rene.pokeapilivedatademo.common.utils.ActivityUtils;

public class ListPokemonActivity extends AppCompatActivity {

    SqlScoutServer sqlScoutServer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listpokemon);
        sqlScoutServer = SqlScoutServer.create(this, getPackageName());
        if (savedInstanceState == null){
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),ListPokemonFragment.create(),R.id.container);
        }
    }
}
