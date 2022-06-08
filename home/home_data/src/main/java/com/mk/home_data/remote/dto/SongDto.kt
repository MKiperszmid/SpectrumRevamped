package com.mk.home_data.remote.dto

import com.squareup.moshi.Json

data class SongDto(
    @field:Json(name = "album")
    val album: AlbumDto,
    @field:Json(name = "artist")
    val artist: ArtistDto,
    @field:Json(name = "duration")
    val duration: Int,
    @field:Json(name = "explicit_content_cover")
    val explicitContentCover: Int,
    @field:Json(name = "explicit_content_lyrics")
    val explicitContentLyrics: Int,
    @field:Json(name = "explicit_lyrics")
    val explicitLyrics: Boolean,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "link")
    val link: String,
    @field:Json(name = "md5_image")
    val md5Image: String,
    @field:Json(name = "position")
    val position: Int,
    @field:Json(name = "preview")
    val preview: String,
    @field:Json(name = "rank")
    val rank: Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "title_short")
    val titleShort: String,
    @field:Json(name = "title_version")
    val titleVersion: String,
    @field:Json(name = "type")
    val type: String
)