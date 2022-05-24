package com.mk.home_data.pager

import com.mk.home_data.remote.DeezerApi
import com.mk.home_data.remote.dto.SongListDto

class PopularArgentinaDataSource(private val api: DeezerApi) : ApiDataSource(api) {
    override suspend fun apiCall(index: Int): SongListDto {
        return api.getPopularArgentina(index)
    }
}