package com.mk.player_presentation.service

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.media.MediaMetadata
import android.media.MediaPlayer
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.mk.player_domain.model.Song
import com.mk.player_presentation.model.TrackList
import com.mk.player_presentation.notification.PlayerNotification
import com.mk.player_presentation.utils.Constants
import com.mk.player_presentation.utils.Constants.ACTION_LOAD_SONGS
import com.mk.player_presentation.utils.Constants.ACTION_NEXT
import com.mk.player_presentation.utils.Constants.ACTION_PLAY_OR_PAUSE
import com.mk.player_presentation.utils.Constants.ACTION_PREVIOUS
import com.mk.player_presentation.utils.Constants.NOTIFICATION_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MusicService : LifecycleService(), MediaPlayer.OnCompletionListener,
    MediaPlayer.OnPreparedListener {
    @Inject
    lateinit var playerNotification: PlayerNotification

    @Inject
    lateinit var playerController: MediaPlayerController

    private lateinit var mediaSessionCompat: MediaSessionCompat
    private var job: Job? = null

    companion object {
        var state by mutableStateOf(MusicPlayerState())
            private set
    }

    override fun onCreate() {
        super.onCreate()
        mediaSessionCompat = MediaSessionCompat(this, "MediaTag")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_LOAD_SONGS -> {
                    val extras = it.extras
                    val parcelableSong =
                        extras?.getParcelable<Song>(Constants.SONG_KEY) ?: return@let
                    val parcelableTrackList =
                        extras.getParcelable(Constants.TRACKLIST_KEY) ?: TrackList()
                    state = state.copy(trackList = parcelableTrackList.trackList)
                    if (parcelableSong == state.currentSong) return@let
                    state = state.copy(currentSong = parcelableSong)
                    loadSong(parcelableSong)
                }
                ACTION_PLAY_OR_PAUSE -> {
                    togglePlayPause()
                }
                ACTION_PREVIOUS -> {
                    loadSong(playerController.previousSong())
                }
                ACTION_NEXT -> {
                    loadSong(playerController.nextSong())
                }
                else -> {}
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun togglePlayPause() {
        playerController.handlePlayPause()
        updateCurrentPlayingTime()
        state = state.copy(isPlaying = playerController.isPlaying())
        startService(state.currentSong, false)
    }

    private fun loadSong(song: Song?) {
        song?.let {
            playerController.reset(it.preview, this, this)
            state = state.copy(currentSong = it)
            startService(it, true)
        }
    }

    private fun startService(song: Song, songLoad: Boolean) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        lifecycleScope.launch {
            val loader = ImageLoader(this@MusicService)
            val request = ImageRequest.Builder(this@MusicService)
                .data(song.image)
                .allowHardware(false) // Disable hardware bitmaps.
                .build()

            val result = (loader.execute(request) as SuccessResult).drawable
            val bitmap = (result as BitmapDrawable).bitmap

            mediaSessionCompat.setMetadata(
                MediaMetadataCompat.Builder()
                    .putString(MediaMetadata.METADATA_KEY_TITLE, song.title)
                    .putString(MediaMetadata.METADATA_KEY_ARTIST, song.artist.name)
                    .putBitmap(MediaMetadata.METADATA_KEY_ALBUM_ART, bitmap).build()
            )
            val notification = playerNotification.createNotification(
                context = this@MusicService,
                session = mediaSessionCompat,
                notificationManager = notificationManager,
                isPlaying = if(songLoad) true else playerController.isPlaying()
            )
            startForeground(NOTIFICATION_ID, notification)
        }
    }

    //TODO: The service isn't stopping correctly.. investigate why
    override fun onDestroy() {
        super.onDestroy()
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(NOTIFICATION_ID)
        playerController.release()
        job?.cancel()
        job = null
        stopForeground(true)
        stopSelf()
    }

    override fun onCompletion(mp: MediaPlayer?) {
        state = state.copy(isPlaying = false)
        if (state.repeatState == LoopState.SONG) {
            loadSong(state.currentSong)
        } else {
            loadSong(playerController.nextSong())
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        playerController.start()
        state = state.copy(isPlaying = playerController.isPlaying())
        updateCurrentPlayingTime()
    }

    //TODO: Maybe move this to the player controller
    private fun updateCurrentPlayingTime() {
        job?.cancel()
        job = lifecycleScope.launch {
            while (true) {
                state = state.copy(
                    currentSeconds = playerController.currentTime()
                )
                delay(1000)
            }
        }
    }
}