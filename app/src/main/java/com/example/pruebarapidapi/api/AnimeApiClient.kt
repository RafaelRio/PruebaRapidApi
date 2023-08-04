package com.example.pruebarapidapi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object AnimeApiClient {
    private const val BASE_URL = "https://anime-db.p.rapidapi.com/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val animeApiService: APIEndpoints = retrofit.create(APIEndpoints::class.java)
}