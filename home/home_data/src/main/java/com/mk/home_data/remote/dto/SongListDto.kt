package com.mk.home_data.remote.dto


import com.squareup.moshi.Json

data class SongListDto(
    @Json(name = "data")
    val data: List<SongDto>,
    @Json(name = "total")
    val total: Int
)