package com.example.pruebarapidapi.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pruebarapidapi.models.AnimeItem
import com.example.pruebarapidapi.paging.AnimePagingSource
import com.example.pruebarapidapi.retrofit.AnimeAPI
import com.example.pruebarapidapi.util.Constants
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class AnimeRepository @Inject constructor(
    private val animeAPI: AnimeAPI,
) {
    private val pageState = PaginationState(currentPagePosition = null)

    fun jumpToPage(page: Int?) {
        pageState.currentPagePosition = page
    }

    private fun createPagingSource(): AnimePagingSource {
        return AnimePagingSource(animeApi = animeAPI, initialPage = 1)
    }

    fun getAnimesFlow() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { createPagingSource() },
    ).flow

    suspend fun getAnimeById(id: Int): AnimeItem {
        val response = animeAPI.getAnimeByid(apiKey = Constants.apiKey, id = id)
        return response
    }
}

class PaginationState(var currentPagePosition: Int?)