package com.mk.player_presentation.model

import android.os.Parcelable
import com.mk.player_domain.model.Song
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrackList(
    val trackList: List<Song> = emptyList()
) : Parcelable