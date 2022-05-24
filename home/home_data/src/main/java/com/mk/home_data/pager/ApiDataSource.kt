package com.mk.home_data.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mk.home_data.mapper.toDomain
import com.mk.home_data.remote.DeezerApi
import com.mk.home_data.remote.dto.SongListDto
import com.mk.home_domain.model.Song

abstract class ApiDataSource(
    private val api: DeezerApi
): PagingSource<Int, Song>() {
    override fun getRefreshKey(state: PagingState<Int, Song>) = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Song> {
        return try {
            val pageNumber = (params.key ?: 0)
            val response = apiCall(pageNumber * DeezerApi.PAGE_SIZE)

            LoadResult.Page(
                data = response.data.map { it.toDomain() },
                prevKey = if (pageNumber > 0) pageNumber - 1 else null,
                nextKey = if (!response.next.isNullOrEmpty()) pageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    abstract suspend fun apiCall(index: Int): SongListDto
}