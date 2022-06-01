package com.mk.player_presentation.service

import com.mk.player_domain.model.Artist
import com.mk.player_domain.model.Song

data class MusicPlayerState(
    val currentSong: Song = emptySong,
    val trackList: List<Song> = emptyList(),
    val shouldSuffle: Boolean = false,
    val repeatState: LoopState = LoopState.NONE,
    val isPlaying: Boolean = false,
    val currentSeconds: Int = 0
)

private val emptySong = Song(
    title = "",
    preview = "",
    duration = 0,
    artist = Artist(
        id = 0,
        name = ""
    ),
    image = ""
)