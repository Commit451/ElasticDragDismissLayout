package com.commit451.elasticdragdismisslayout.sample.api;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;

import retrofit2.Retrofit;

/**
 * Api Client
 */
public class ApiClient {

    private static MetalGearSolidApi sMetalGearSolidApi;

    public static MetalGearSolidApi instance() {
        if (sMetalGearSolidApi == null) {
            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com")
                    .addConverterFactory(LoganSquareConverterFactory.create())
                    .build();
            sMetalGearSolidApi = restAdapter.create(MetalGearSolidApi.class);
        }
        return sMetalGearSolidApi;
    }
}
