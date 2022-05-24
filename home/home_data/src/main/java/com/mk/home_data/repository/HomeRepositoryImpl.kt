package com.mk.home_data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mk.home_data.mapper.toDomain
import com.mk.home_data.pager.SongDataSource
import com.mk.home_data.remote.DeezerApi
import com.mk.home_data.remote.dto.SongListDto
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

    override suspend fun getTopSongs(): Result<List<Song>> {
        return try {
            mapSongs(api.getTopSongs())
        } catch (e: Exception) {
            showError(e)
        }
    }

    override suspend fun getPopularArgentina(): Flow<PagingData<Song>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH,
                initialLoadSize = PAGE_SIZE
            ),
            pagingSourceFactory = { SongDataSource(api) }
        ).flow
    }

    private fun mapSongs(songList: SongListDto): Result<List<Song>> {
        val mapped = songList.data.map { it.toDomain() }
        return Result.success(mapped)
    }

    private fun showError(exception: java.lang.Exception): Result<List<Song>> {
        exception.printStackTrace()
        return Result.failure(exception)
    }
}