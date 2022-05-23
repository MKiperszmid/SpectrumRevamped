package com.mk.home_data.remote.dto


import com.squareup.moshi.Json

data class AlbumDto(
    @Json(name = "cover")
    val cover: String,
    @Json(name = "cover_big")
    val coverBig: String,
    @Json(name = "cover_medium")
    val coverMedium: String,
    @Json(name = "cover_small")
    val coverSmall: String,
    @Json(name = "cover_xl")
    val coverXl: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "md5_image")
    val md5Image: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "tracklist")
    val tracklist: String,
    @Json(name = "type")
    val type: String
)