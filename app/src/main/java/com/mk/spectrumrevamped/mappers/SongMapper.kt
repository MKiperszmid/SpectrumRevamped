package com.mk.spectrumrevamped.mappers

import com.mk.home_domain.model.Artist
import com.mk.home_domain.model.Song

//This is made so every module has their own models. Maybe unify all?

fun Song.toPlayer(): com.mk.player_domain.model.Song {
    return com.mk.player_domain.model.Song(
        title = this.title,
        preview = this.preview,
        duration = this.duration,
        artist = this.artist.toPlayer(),
        image = this.image
    )
}

fun Artist.toPlayer(): com.mk.player_domain.model.Artist {
    return com.mk.player_domain.model.Artist(
        id = this.id,
        name = this.name
    )
}

fun com.mk.seach_domain.model.Song.toPlayer(): com.mk.player_domain.model.Song {
    return com.mk.player_domain.model.Song(
        title = this.title,
        preview = this.preview,
        duration = this.duration,
        artist = this.artist.toPlayer(),
        image = this.image
    )
}

fun com.mk.seach_domain.model.Artist.toPlayer(): com.mk.player_domain.model.Artist {
    return com.mk.player_domain.model.Artist(
        id = this.id,
        name = this.name
    )
}