package com.example.pruebarapidapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pruebarapidapi.models.AnimeItem
import com.example.pruebarapidapi.models.Genre
import com.example.pruebarapidapi.repository.AnimeRepository
import com.example.pruebarapidapi.retrofit.AnimeAPI
import com.example.pruebarapidapi.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
) : ViewModel() {

    private val _filtersState = MutableStateFlow(AnimeFiltersState())
    val filtersState = _filtersState.asStateFlow()

    fun getAllAnimes(title: String = ""): Flow<PagingData<AnimeItem>> {
        return animeRepository
            .getAnimesFlow(title)
            .cachedIn(viewModelScope)
    }

    suspend fun getGenres() {
        animeRepository.getGenres()
    }

}

data class AnimeFiltersState(
    var query: String = "",
)