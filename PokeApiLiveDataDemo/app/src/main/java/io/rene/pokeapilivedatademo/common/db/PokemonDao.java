package io.rene.pokeapilivedatademo.common.db;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PagedList;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

import io.rene.pokeapilivedatademo.common.models.Pokemon;

@Dao
public interface PokemonDao {

    @Query("SELECT * FROM POKEMON ORDER BY ID")
    DataSource.Factory<Integer, Pokemon> pokemons();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Pokemon> pokemons);

}
