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

    suspend fun getAnimeList(page: Int, size: Int): Result<List<AnimeItem>> {
        return try {
            val response = animeAPI.getAnimeList(Constants.apiKey, page, size)

            Result.success(response.animeList)
        }
        catch (e: IOException) {
            Result.failure(e)
        }
        catch (e: HttpException) {
            Result.failure(e)
        }
        catch (e: CancellationException) {
            throw e
        }
        catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class PaginationState(var currentPagePosition: Int?)