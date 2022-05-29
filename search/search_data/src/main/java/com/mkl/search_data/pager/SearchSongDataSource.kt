package com.mkl.search_data.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mk.seach_domain.model.Song
import com.mkl.search_data.mapper.toDomain
import com.mkl.search_data.remote.DeezerApi

class SearchSongDataSource(
    private val query: String,
    private val api: DeezerApi
) : PagingSource<Int, Song>() {
    override fun getRefreshKey(state: PagingState<Int, Song>) = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Song> {
        return try {
            val pageNumber = (params.key ?: 0)
            val response = api.searchSong(query, pageNumber * DeezerApi.PAGE_SIZE)

            LoadResult.Page(
                data = response.data.map { it.toDomain() },
                prevKey = if (pageNumber > 0) pageNumber - 1 else null,
                nextKey = if (!response.next.isNullOrEmpty()) pageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}