package com.mkl.search_data.remote.dto

import com.squareup.moshi.Json

data class ArtistDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "link")
    val link: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "picture")
    val picture: String,
    @Json(name = "picture_big")
    val pictureBig: String,
    @Json(name = "picture_medium")
    val pictureMedium: String,
    @Json(name = "picture_small")
    val pictureSmall: String,
    @Json(name = "picture_xl")
    val pictureXl: String,
    @Json(name = "radio")
    val radio: Boolean,
    @Json(name = "tracklist")
    val tracklist: String,
    @Json(name = "type")
    val type: String
)