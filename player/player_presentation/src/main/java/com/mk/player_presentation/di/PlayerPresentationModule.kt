package com.mk.player_presentation.di

import android.media.AudioAttributes
import android.media.MediaPlayer
import com.mk.player_presentation.notification.PlayerNotification
import com.mk.player_presentation.service.MediaPlayerController
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

    @Provides
    @ServiceScoped
    fun provideMediaPlayer(): MediaPlayer {
        return MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA).build()
            )
        }
    }

    @Provides
    @ServiceScoped
    fun provideMediaPlayerController(mediaPlayer: MediaPlayer): MediaPlayerController {
        return MediaPlayerController(mediaPlayer)
    }
}