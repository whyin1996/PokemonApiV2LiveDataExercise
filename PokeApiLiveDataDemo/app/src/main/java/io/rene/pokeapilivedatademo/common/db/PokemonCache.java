package io.rene.pokeapilivedatademo.common.db;

import android.arch.paging.DataSource;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.rene.pokeapilivedatademo.common.models.Pokemon;

public class PokemonCache {

    private PokemonDao mDao;
    private Executor mIOExecutor;

    private static final Object LOCK = new Object();

    public interface InsertionCallback{
        void onInsertionCompleted();
    }

    public interface QueryCallback {
        void onResult(DataSource.Factory<String, Pokemon> dataFactory);
    }

    public PokemonCache(PokemonDao mDao) {
        this.mDao = mDao;
        this.mIOExecutor = Executors.newSingleThreadExecutor();
    }


    public DataSource.Factory<Integer, Pokemon> pokemons() {
        return mDao.pokemons();
    }


    public synchronized void insert(final List<Pokemon> pokemons, final InsertionCallback callback) {
        mIOExecutor.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (LOCK){
                    mDao.insert(pokemons);
                    callback.onInsertionCompleted();
                }
            }
        });
    }
}
