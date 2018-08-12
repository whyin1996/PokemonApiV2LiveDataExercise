package io.rene.pokeapilivedatademo.common.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonApi {

    private static final String BASE_URL = "http://pokeapi.salestock.net/api/";
    private static Retrofit INSTANCE;
    private static final Object INSTANCE_LOCK = new Object();

    public static <T> T get(Class<T> cls){
        synchronized (INSTANCE_LOCK){
            if (INSTANCE == null){
                INSTANCE = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }
        return INSTANCE.create(cls);
    }

}
