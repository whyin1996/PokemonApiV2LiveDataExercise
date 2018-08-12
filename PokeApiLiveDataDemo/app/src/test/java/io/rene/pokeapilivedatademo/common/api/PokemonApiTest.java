package io.rene.pokeapilivedatademo.common.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;

import io.rene.pokeapilivedatademo.common.models.PokemonResult;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonApiTest {

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void get() throws Exception {
        System.out.println("TTT: GET");
        PokemonV2Service service = PokemonApi.get(PokemonV2Service.class);
        Response<PokemonResult> responseBody = service.pokemons().execute();
        System.out.println("TTT: " + responseBody.body().getCount());

    }
}