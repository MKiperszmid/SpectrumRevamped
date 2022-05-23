package com.mk.home_data.repository

import com.mk.home_data.mapper.toDomain
import com.mk.home_data.remote.DeezerApi
import com.mk.home_domain.model.Song
import com.mk.home_domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val api: DeezerApi
) : HomeRepository {
    override suspend fun getTopSongs(): Result<List<Song>> {
        return try {
            val songs = api.getTopSongs()
            val mapped = songs.data.map { it.toDomain() }
            return Result.success(mapped)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}