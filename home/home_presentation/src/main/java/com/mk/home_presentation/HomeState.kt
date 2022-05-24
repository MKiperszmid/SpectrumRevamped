package com.mk.home_presentation

import androidx.paging.PagingData
import com.mk.home_domain.model.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeState(
    val topSongs: Flow<PagingData<Song>> = emptyFlow(),
    val popularArgentina: Flow<PagingData<Song>> = emptyFlow()
)
