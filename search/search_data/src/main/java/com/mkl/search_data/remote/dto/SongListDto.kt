package com.mkl.search_data.remote.dto

import com.squareup.moshi.Json

data class SongListDto(
    @Json(name = "data")
    val data: List<SongDto>,
    @Json(name = "total")
    val total: Int,
    @Json(name = "next")
    val next: String?,
    @Json(name = "prev")
    val prev: String?
)