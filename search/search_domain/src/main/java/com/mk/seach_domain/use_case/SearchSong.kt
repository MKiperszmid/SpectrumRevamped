package com.mk.seach_domain.use_case

import androidx.paging.PagingData
import com.mk.seach_domain.model.Song
import com.mk.seach_domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class SearchSong(
    private val repository: SearchRepository
) {
    operator fun invoke(query: String): Flow<PagingData<Song>> {
        if (query.isBlank()) {
            return emptyFlow()
        }
        return repository.searchSong(query.trim())
    }
}