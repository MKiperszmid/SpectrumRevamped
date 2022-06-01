package com.mk.player_presentation.service

import com.mk.player_domain.model.Song
import com.mk.player_presentation.model.TrackList

data class MusicPlayerState(
    val currentSong: Song? = null,
    val trackList: List<Song> = emptyList(),
    val shouldSuffle: Boolean = false,
    val repeatState: LoopState = LoopState.LoopSong,
    val isPlaying: Boolean = false
)
