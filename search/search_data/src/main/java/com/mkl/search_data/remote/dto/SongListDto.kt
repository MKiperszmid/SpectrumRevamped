package com.mkl.search_data.remote.dto

import com.squareup.moshi.Json

data class SongListDto(
    @field:Json(name = "data")
    val data: List<SongDto>,
    @field:Json(name = "total")
    val total: Int,
    @field:Json(name = "next")
    val next: String?,
    @field:Json(name = "prev")
    val prev: String?
)