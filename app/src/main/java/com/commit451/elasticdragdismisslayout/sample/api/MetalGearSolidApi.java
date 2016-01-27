package com.commit451.elasticdragdismisslayout.sample.api;

import com.commit451.elasticdragdismisslayout.sample.api.response.GamesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * The fake API
 */
public interface MetalGearSolidApi {

    @GET("Jawnnypoo/FakeMetalGearSolidAPI/master/mgs.json")
    Call<GamesResponse> getGames();

}
