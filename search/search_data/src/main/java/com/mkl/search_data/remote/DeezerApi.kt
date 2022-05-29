package com.mkl.search_data.remote

import com.mkl.search_data.remote.dto.SongListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DeezerApi {
    companion object {
        const val BASE_URL = "https://api.deezer.com/"
        const val PAGE_SIZE = 25
    }

    @GET("search/track")
    suspend fun searchSong(@Query("q") query: String, @Query("index") index: Int): SongListDto
}