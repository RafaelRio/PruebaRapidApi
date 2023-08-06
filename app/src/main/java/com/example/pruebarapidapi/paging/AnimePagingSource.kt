package com.example.pruebarapidapi.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pruebarapidapi.models.AnimeItem
import com.example.pruebarapidapi.retrofit.AnimeAPI
import com.example.pruebarapidapi.util.Constants

class AnimePagingSource(
    private val animeApi: AnimeAPI,
    private val initialPage: Int,
    private val title: String
) : PagingSource<Int, AnimeItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeItem> {
        val position = params.key ?: initialPage
        return try {
            val response = animeApi.getAnimeList(Constants.animeApiKey, position, params.loadSize, title)
            val animeList = response.animeList
            val nextKey = if (animeList.isEmpty()) {
                null
            } else {
                position + 1
            }

            LoadResult.Page(
                data = animeList,
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AnimeItem>): Int? {

        val anchorPosition = state.anchorPosition ?: return null

        return state.closestPageToPosition(anchorPosition)?.run {
            prevKey?.plus(1) ?: nextKey?.minus(1)
        }
    }
}