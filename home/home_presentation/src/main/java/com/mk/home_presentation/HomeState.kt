package com.mk.home_presentation

import com.mk.home_domain.model.Song

data class HomeState(
    val topSongs: List<Song> = emptyList(),
    val popularArgentina: List<Song> = emptyList(),
    val isLoading: Boolean = false
)
