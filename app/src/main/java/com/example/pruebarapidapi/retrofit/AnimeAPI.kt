package com.example.pruebarapidapi.retrofit

import com.example.pruebarapidapi.models.AnimeItem
import com.example.pruebarapidapi.models.AnimeResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface AnimeAPI {

    @GET("anime")
    suspend fun getAnimeList(
        @Header("X-RapidAPI-Key") apiKey: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): AnimeResponse

    @GET("anime/by-id")
    suspend fun getAnimeByid(
        @Header("X-RapidAPI-Key") apiKey: String,
        @Query("id") id: Int
    ): AnimeItem
}