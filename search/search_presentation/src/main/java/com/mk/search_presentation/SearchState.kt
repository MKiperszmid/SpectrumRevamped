package com.mk.search_presentation

import androidx.paging.PagingData
import com.mk.seach_domain.model.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val results: Flow<PagingData<Song>> = emptyFlow()
)
