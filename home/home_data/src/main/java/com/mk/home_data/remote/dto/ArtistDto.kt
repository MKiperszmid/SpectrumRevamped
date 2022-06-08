package com.mk.home_data.remote.dto

import com.squareup.moshi.Json

data class ArtistDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "link")
    val link: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "picture")
    val picture: String,
    @field:Json(name = "picture_big")
    val pictureBig: String,
    @field:Json(name = "picture_medium")
    val pictureMedium: String,
    @field:Json(name = "picture_small")
    val pictureSmall: String,
    @field:Json(name = "picture_xl")
    val pictureXl: String,
    @field:Json(name = "radio")
    val radio: Boolean,
    @field:Json(name = "tracklist")
    val tracklist: String,
    @field:Json(name = "type")
    val type: String
)