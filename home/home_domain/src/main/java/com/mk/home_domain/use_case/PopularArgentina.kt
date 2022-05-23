package com.mk.home_domain.use_case

import com.mk.home_domain.model.Song
import com.mk.home_domain.repository.HomeRepository

class PopularArgentina(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Result<List<Song>> {
        return repository.getPopularArgentina()
    }
}