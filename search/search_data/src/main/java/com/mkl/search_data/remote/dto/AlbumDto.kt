package com.mkl.search_data.remote.dto


import com.squareup.moshi.Json

data class AlbumDto(
    @field:Json(name = "cover")
    val cover: String,
    @field:Json(name = "cover_big")
    val coverBig: String,
    @field:Json(name = "cover_medium")
    val coverMedium: String,
    @field:Json(name = "cover_small")
    val coverSmall: String,
    @field:Json(name = "cover_xl")
    val coverXl: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "md5_image")
    val md5Image: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "tracklist")
    val tracklist: String,
    @field:Json(name = "type")
    val type: String
)