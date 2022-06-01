package com.mk.player_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(
    val title: String,
    val preview: String,
    val duration: Int,
    val artist: Artist,
    val image: String
): Parcelable
