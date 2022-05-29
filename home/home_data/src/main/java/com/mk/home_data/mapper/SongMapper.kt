package com.mk.home_data.mapper

import com.mk.home_data.remote.dto.SongDto
import com.mk.home_domain.model.Song

fun SongDto.toDomain(): Song {
    return Song(
        id = this.id,
        title = this.title,
        preview = this.preview,
        duration = this.duration,
        artist = this.artist.toDomain(),
        image = this.album.cover
    )
}