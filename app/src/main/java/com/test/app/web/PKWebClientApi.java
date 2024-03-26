package com.test.app.web;

import com.test.app.models.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PKWebClientApi {

    @GET("pokemon")
    Call<PKResponse> getPokemonPaged(@Query("offset") int offset, @Query("limit") int limit);

    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") int id);
}
