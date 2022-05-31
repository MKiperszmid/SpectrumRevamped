package com.mk.player_presentation.service

sealed class LoopState {
    object None: LoopState()
    object LoopSong: LoopState()
    object LoopTracks: LoopState()
}
