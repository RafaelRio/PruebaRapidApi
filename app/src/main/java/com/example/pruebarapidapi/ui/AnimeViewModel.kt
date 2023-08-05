package com.example.pruebarapidapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pruebarapidapi.models.AnimeItem
import com.example.pruebarapidapi.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
) : ViewModel() {

    fun getAllAnimes(title: String = ""): Flow<PagingData<AnimeItem>> {
        return animeRepository
            .getAnimesFlow(title)
            .cachedIn(viewModelScope)
    }

}