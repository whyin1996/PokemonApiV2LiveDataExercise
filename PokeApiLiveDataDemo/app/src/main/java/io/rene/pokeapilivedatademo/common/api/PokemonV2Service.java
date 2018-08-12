package io.rene.pokeapilivedatademo.common.api;

import io.rene.pokeapilivedatademo.common.models.PokemonResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokemonV2Service {
    @GET("v2/pokemon")
    Call<PokemonResult> pokemons(@Query("offset") Integer offset);
}
