package com.mk.home_domain.use_case

import androidx.paging.PagingData
import com.mk.home_domain.model.Song
import com.mk.home_domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class TopSongs(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Flow<PagingData<Song>> {
        return repository.getTopSongs()
    }
}