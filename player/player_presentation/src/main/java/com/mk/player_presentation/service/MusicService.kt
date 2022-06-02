package com.mk.player_presentation.service

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
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

    private var job: Job? = null

    companion object {
        var state by mutableStateOf(MusicPlayerState())
            private set
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
    }

    private fun loadSong(song: Song?) {
        song?.let {
            playerController.reset(it.preview, this, this)
            state = state.copy(currentSong = it)
            startService(it)
        }
    }

    private fun startService(song: Song) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = playerNotification.createNotification(
            song = song,
            context = this,
            notificationManager = notificationManager
        )
        startForeground(NOTIFICATION_ID, notification)
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