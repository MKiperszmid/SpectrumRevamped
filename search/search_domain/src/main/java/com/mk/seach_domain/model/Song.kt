package com.mk.seach_domain.model

data class Song(
    val id: Int,
    val title: String,
    val preview: String,
    val duration: Int,
    val artist: Artist,
    val image: String
)
