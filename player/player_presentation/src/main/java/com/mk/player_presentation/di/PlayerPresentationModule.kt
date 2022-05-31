package com.mk.player_presentation.di

import com.mk.player_presentation.notification.PlayerNotification
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped

@InstallIn(ServiceComponent::class)
@Module
object PlayerPresentationModule {
    @Provides
    @ServiceScoped
    fun providePlayerNotification(): PlayerNotification {
        return PlayerNotification()
    }
}