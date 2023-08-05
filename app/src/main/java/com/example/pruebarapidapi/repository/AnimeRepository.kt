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

    private fun createPagingSource(title: String): AnimePagingSource {
        return AnimePagingSource(animeApi = animeAPI, initialPage = 1, title = title)
    }

    fun getAnimesFlow(title: String) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { createPagingSource(title = title) },
    ).flow
}

class PaginationState(var currentPagePosition: Int?)