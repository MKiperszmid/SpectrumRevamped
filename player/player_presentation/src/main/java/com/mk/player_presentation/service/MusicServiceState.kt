package com.mk.player_presentation.service

import com.mk.player_domain.model.Artist
import com.mk.player_domain.model.Song

//TODO: Should the ViewModel handle the state, or should the Service do it?
data class MusicServiceState(
    val currentSong: Song = defaultSong, //TODO: Reeplace with a nullable Song maybe?
    val trackList: List<Song> = emptyList(),
    val shouldSuffle: Boolean = false,
    val repeatState: LoopState = LoopState.None,
    val isPlaying: Boolean = false
)

private val defaultSong = Song(
    title = "Tacones Rojos",
    preview = "https://cdns-preview-b.dzcdn.net/stream/c-b4c5609f35dd02d6f1761a9d4f65351c-4.mp3",
    duration = 189,
    artist = Artist(
        id = 1522667852,
        name = "Sebastián Yatra"
    ),
    image = "https://e-cdns-images.dzcdn.net/images/cover/00c297f61a9a7af15833daf8ec87cc8c/500x500-000000-80-0-0.jpg"
)