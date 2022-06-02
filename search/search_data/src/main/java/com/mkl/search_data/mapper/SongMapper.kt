package com.mkl.search_data.mapper

import com.mk.seach_domain.model.Song
import com.mkl.search_data.remote.dto.SongDto

fun SongDto.toDomain(): Song {
    return Song(
        id = this.id,
        title = this.title,
        preview = this.preview,
        duration = this.duration,
        artist = this.artist.toDomain(),
        image = this.album.coverMedium
    )
}