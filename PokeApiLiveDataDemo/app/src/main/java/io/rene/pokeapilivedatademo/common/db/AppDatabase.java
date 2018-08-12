package io.rene.pokeapilivedatademo.common.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import io.rene.pokeapilivedatademo.common.models.Pokemon;

@Database(version = 1, entities = Pokemon.class, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    public abstract PokemonDao pokemonDao();

    public volatile static AppDatabase INSTANCE;

    private static final Object LOCK = new Object();

    public static AppDatabase getInstance(Context context){
        synchronized (LOCK){
            if (INSTANCE == null){
                INSTANCE = buildDatabase(context);
            }
            return INSTANCE;
        }
    }

    private static AppDatabase buildDatabase(Context context){
        return Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "APP.db")
            .build();
    }

}
