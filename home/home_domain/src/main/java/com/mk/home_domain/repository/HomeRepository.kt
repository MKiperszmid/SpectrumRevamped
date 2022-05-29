package com.mk.home_domain.repository

import androidx.paging.PagingData
import com.mk.home_domain.model.Song
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getTopSongs(): Flow<PagingData<Song>>
    fun getPopularArgentina(): Flow<PagingData<Song>>
}