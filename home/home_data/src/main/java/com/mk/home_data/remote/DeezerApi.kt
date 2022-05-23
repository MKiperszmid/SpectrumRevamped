package com.mk.home_data.remote

import com.mk.home_data.remote.dto.SongListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DeezerApi {
    companion object {
        const val BASE_URL = "https://api.deezer.com/"
        const val PAGE_SIZE = 25
    }

    @GET("chart/0/tracks")
    suspend fun getTopSongs(): SongListDto

    @GET("playlist/1279119721/tracks")
    suspend fun getPopularArgentina(@Query("index") index: Int): SongListDto
}