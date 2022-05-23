package com.mk.home_data.mapper

import com.mk.home_data.remote.dto.ArtistDto
import com.mk.home_domain.model.Artist

fun ArtistDto.toDomain(): Artist {
    return Artist(
        id = this.id,
        name = this.name,
        picture = this.picture
    )
}