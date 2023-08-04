package com.example.pruebarapidapi.api

import com.example.pruebarapidapi.api.AnimeApiClient.animeApiService
import com.example.pruebarapidapi.model.AnimeItem
import com.example.pruebarapidapi.model.AnimeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

object API {
    private const val apiKey = "1976adcfdcmsh2e6a71dec3bc62cp14da3bjsnfd5db52032ba"

    suspend fun getAnimeList(page: Int, size: Int): List<AnimeItem> {
        return withContext(Dispatchers.IO) {
            val call: Call<AnimeResponse>? = animeApiService.getAnimeList(apiKey, page, size)
            val response: Response<AnimeResponse> = call?.execute() ?: throw IOException("Error en la llamada")
            if (response.isSuccessful) {
                val animeResponse: AnimeResponse? = response.body()
                animeResponse?.animeList ?: emptyList()
            } else {
                emptyList()
            }
        }
    }
}