package com.mk.player_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artist(
    val id: Int,
    val name: String
) : Parcelable
