package com.mk.player_presentation

import com.mk.player_domain.model.Song

data class PlayerState(
    val song: Song? = null,
    val isPlaying: Boolean = false
)
