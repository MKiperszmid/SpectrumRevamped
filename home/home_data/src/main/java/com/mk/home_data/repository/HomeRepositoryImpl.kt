package com.mk.home_data.repository

import com.mk.home_data.mapper.toDomain
import com.mk.home_data.remote.DeezerApi
import com.mk.home_data.remote.dto.SongListDto
import com.mk.home_domain.model.Song
import com.mk.home_domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val api: DeezerApi
) : HomeRepository {
    override suspend fun getTopSongs(): Result<List<Song>> {
        return try {
            mapSongs(api.getTopSongs())
        } catch (e: Exception) {
            showError(e)
        }
    }

    override suspend fun getPopularArgentina(page: Int): Result<List<Song>> {
        return try {
            val popular = api.getPopularArgentina(page * DeezerApi.PAGE_SIZE)
            mapSongs(popular)
        } catch (e: Exception) {
            showError(e)
        }
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