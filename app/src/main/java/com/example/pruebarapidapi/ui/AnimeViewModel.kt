package com.example.pruebarapidapi.ui

import androidx.lifecycle.ViewModel
import com.example.pruebarapidapi.api.API
import com.example.pruebarapidapi.model.AnimeItem

class AnimeViewModel : ViewModel() {

    suspend fun getAnimes() : List<AnimeItem>? {
        return API.getAnimeList(page = 1, size = 10)
    }
}