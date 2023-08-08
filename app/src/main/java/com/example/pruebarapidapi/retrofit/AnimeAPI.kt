package com.example.pruebarapidapi.retrofit

import com.example.pruebarapidapi.models.AnimeItem
import com.example.pruebarapidapi.models.AnimeResponse
import com.example.pruebarapidapi.models.Genre
import com.example.pruebarapidapi.models.Genres
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface AnimeAPI {

    @GET("anime")
    suspend fun getAnimeList(
        @Header("X-RapidAPI-Key") apiKey: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("search") title: String = ""
    ): AnimeResponse

    @GET("genre")
    suspend fun getGenres(
        @Header("X-RapidAPI-Key") apiKey: String,
    ): List<Genre>
}