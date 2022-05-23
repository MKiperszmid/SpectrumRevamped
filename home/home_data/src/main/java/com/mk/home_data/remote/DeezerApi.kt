package com.mk.home_data.remote

import com.mk.home_data.remote.dto.SongListDto
import retrofit2.http.GET

interface DeezerApi {
    companion object {
        const val BASE_URL = "https://api.deezer.com/"
    }

    @GET("chart/0/tracks")
    suspend fun getTopSongs(): SongListDto
}