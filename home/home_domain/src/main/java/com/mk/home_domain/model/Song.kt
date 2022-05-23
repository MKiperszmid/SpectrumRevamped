package com.mk.home_domain.model

data class Song(
    val title: String,
    val preview: String,
    val duration: Int,
    val artist: Artist,
    val image: String
)