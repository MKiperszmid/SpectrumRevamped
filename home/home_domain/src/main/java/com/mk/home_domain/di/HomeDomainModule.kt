package com.mk.home_domain.di

import com.mk.home_domain.repository.HomeRepository
import com.mk.home_domain.use_case.HomeUseCases
import com.mk.home_domain.use_case.PopularArgentina
import com.mk.home_domain.use_case.TopSongs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object HomeDomainModule {
    @Provides
    @ViewModelScoped
    fun provideHomeUseCases(repository: HomeRepository): HomeUseCases {
        return HomeUseCases(
            topSongs = TopSongs(repository),
            popularArgentina = PopularArgentina(repository)
        )
    }
}