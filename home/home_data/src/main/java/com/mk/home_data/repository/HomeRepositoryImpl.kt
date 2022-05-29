package com.mk.home_data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mk.home_data.pager.ApiDataSource
import com.mk.home_data.pager.PopularArgentinaDataSource
import com.mk.home_data.pager.TopSongsDataSource
import com.mk.home_data.remote.DeezerApi
import com.mk.home_domain.model.Song
import com.mk.home_domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(
    private val api: DeezerApi
) : HomeRepository {
    private companion object {
        const val PAGE_SIZE = DeezerApi.PAGE_SIZE
        const val PREFETCH = 5
    }

    override fun getTopSongs(): Flow<PagingData<Song>> {
        return getPager(TopSongsDataSource(api)).flow
    }

    override fun getPopularArgentina(): Flow<PagingData<Song>> {
        return getPager(PopularArgentinaDataSource(api)).flow
    }

    private fun getPager(apiDataSource: ApiDataSource): Pager<Int, Song> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH,
                initialLoadSize = PAGE_SIZE
            ),
            pagingSourceFactory = { apiDataSource }
        )
    }
}