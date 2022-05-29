package com.mkl.search_data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mk.seach_domain.model.Song
import com.mk.seach_domain.repository.SearchRepository
import com.mkl.search_data.pager.SearchSongDataSource
import com.mkl.search_data.remote.DeezerApi
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val api: DeezerApi
) : SearchRepository {
    private companion object {
        const val PAGE_SIZE = DeezerApi.PAGE_SIZE
        const val PREFETCH = 5
    }

    override fun searchSong(query: String): Flow<PagingData<Song>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH,
                initialLoadSize = PAGE_SIZE
            ),
            pagingSourceFactory = { SearchSongDataSource(query, api) }
        )
        return pager.flow
    }
}