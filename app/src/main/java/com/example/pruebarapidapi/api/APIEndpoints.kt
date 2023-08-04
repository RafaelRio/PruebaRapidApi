package com.example.pruebarapidapi.api

import com.example.pruebarapidapi.model.AnimeItem
import com.example.pruebarapidapi.model.AnimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface APIEndpoints {

    @GET("anime")
    fun getAnimeList(
        @Header("X-RapidAPI-Key") apiKey: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Call<AnimeResponse>
}