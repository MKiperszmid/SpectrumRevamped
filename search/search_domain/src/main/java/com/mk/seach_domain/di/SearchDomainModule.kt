package com.mk.seach_domain.di

import com.mk.seach_domain.repository.SearchRepository
import com.mk.seach_domain.use_case.SearchSong
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SearchDomainModule {
    @Provides
    @ViewModelScoped
    fun provideSearchSongUseCase(repository: SearchRepository): SearchSong {
        return SearchSong(repository)
    }
}