package com.example.pruebarapidapi.api

import com.example.pruebarapidapi.api.AnimeApiClient.animeApiService
import com.example.pruebarapidapi.model.AnimeItem

object API {
    private const val apiKey = "1976adcfdcmsh2e6a71dec3bc62cp14da3bjsnfd5db52032ba"

    suspend fun getAnimeList(page: Int, size: Int): List<AnimeItem> {
        val response = animeApiService.getAnimeList(apiKey, page, size)
        return response.animeList
    }
}