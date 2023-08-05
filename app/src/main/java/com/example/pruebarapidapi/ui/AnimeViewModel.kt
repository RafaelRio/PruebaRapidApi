package com.example.pruebarapidapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pruebarapidapi.models.AnimeItem
import com.example.pruebarapidapi.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
) : ViewModel() {

    var animeList = animeRepository
        .getAnimesFlow()
        .cachedIn(viewModelScope)

    suspend fun getAnimeByID(id: Int) : AnimeItem {
        return animeRepository.getAnimeById(id)
    }
}