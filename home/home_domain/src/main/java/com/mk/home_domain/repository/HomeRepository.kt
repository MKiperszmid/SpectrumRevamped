package com.mk.home_domain.repository

import com.mk.home_domain.model.Song

interface HomeRepository {
    suspend fun getTopSongs(): Result<List<Song>>
    suspend fun getPopularArgentina(page: Int): Result<List<Song>>
}