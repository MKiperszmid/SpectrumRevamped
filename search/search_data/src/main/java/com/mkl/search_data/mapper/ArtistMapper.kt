package com.mkl.search_data.mapper

import com.mk.seach_domain.model.Artist
import com.mkl.search_data.remote.dto.ArtistDto

fun ArtistDto.toDomain(): Artist {
    return Artist(
        id = this.id,
        name = this.name,
        picture = this.picture
    )
}