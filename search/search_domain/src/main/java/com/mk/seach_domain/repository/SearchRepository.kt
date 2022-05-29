package com.mk.seach_domain.repository

import androidx.paging.PagingData
import com.mk.seach_domain.model.Song
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchSong(query: String): Flow<PagingData<Song>>
}