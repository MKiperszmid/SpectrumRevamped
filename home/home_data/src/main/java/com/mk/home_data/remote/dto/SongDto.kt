package com.mk.home_data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class SongDto(
    @Json(name = "album")
    val album: AlbumDto,
    @Json(name = "artist")
    val artist: ArtistDto,
    @Json(name = "duration")
    val duration: Int,
    @Json(name = "explicit_content_cover")
    val explicitContentCover: Int,
    @Json(name = "explicit_content_lyrics")
    val explicitContentLyrics: Int,
    @Json(name = "explicit_lyrics")
    val explicitLyrics: Boolean,
    @Json(name = "id")
    val id: Int,
    @Json(name = "link")
    val link: String,
    @Json(name = "md5_image")
    val md5Image: String,
    @Json(name = "position")
    val position: Int,
    @Json(name = "preview")
    val preview: String,
    @Json(name = "rank")
    val rank: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "title_short")
    val titleShort: String,
    @Json(name = "title_version")
    val titleVersion: String,
    @Json(name = "type")
    val type: String
)