package com.mk.player_presentation.service

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleService
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
import javax.inject.Inject

@AndroidEntryPoint
class MusicService : LifecycleService(), MediaPlayer.OnCompletionListener,
    MediaPlayer.OnPreparedListener {
    @Inject
    lateinit var playerNotification: PlayerNotification

    private lateinit var mediaPlayer: MediaPlayer

    companion object {
        var state by mutableStateOf(MusicPlayerState())
            private set
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA).build()
            )
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_LOAD_SONGS -> {
                    val extras = it.extras
                    val parcelableSong = extras?.getParcelable<Song>(Constants.SONG_KEY)
                    val parcelableTrackList =
                        extras?.getParcelable(Constants.TRACKLIST_KEY) ?: TrackList()
                    state = state.copy(
                        currentSong = parcelableSong,
                        trackList = parcelableTrackList.trackList
                    )
                    parcelableSong?.let { song ->
                        loadSong(song)
                    }
                }
                ACTION_PLAY_OR_PAUSE -> {
                    togglePlayPause()
                }
                ACTION_PREVIOUS -> {
                    previousSong()
                }
                ACTION_NEXT -> {
                    nextSong()
                }
                else -> {}
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    //TODO: Review nextSong and previousSong logic. See if we can reuse logic
    private fun nextSong() {
        val trackList = state.trackList
        val currentIndex = trackList.indexOf(state.currentSong)
        if (currentIndex + 1 >= trackList.size) {
            if (state.repeatState == LoopState.LoopTracks && trackList.isNotEmpty()) {
                loadSong(trackList.first())
            } else if (state.repeatState == LoopState.LoopSong) {
                loadSong(state.currentSong!!)
            }
        } else {
            loadSong(trackList[currentIndex + 1])
        }
    }

    private fun previousSong() {
        val trackList = state.trackList
        val currentIndex = trackList.indexOf(state.currentSong)
        if (currentIndex - 1 < 0) {
            if (state.repeatState == LoopState.LoopTracks && trackList.isNotEmpty()) {
                loadSong(trackList.last())
            } else if (state.repeatState == LoopState.LoopSong) {
                loadSong(state.currentSong!!)
            }
        } else {
            loadSong(trackList[currentIndex - 1])
        }
    }

    private fun togglePlayPause() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        } else {
            mediaPlayer.start()
        }
        state = state.copy(isPlaying = mediaPlayer.isPlaying)
    }

    private fun loadSong(song: Song) {
        mediaPlayer.apply {
            reset()
            setOnCompletionListener(this@MusicService)
            setOnPreparedListener(this@MusicService)
            setDataSource(song.preview)
            prepareAsync()
        }
        state = state.copy(currentSong = song)
        startService(song)
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

    override fun onDestroy() {
        super.onDestroy()
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(NOTIFICATION_ID)
        mediaPlayer.release()
    }

    override fun onCompletion(mp: MediaPlayer?) {
        if (state.repeatState == LoopState.LoopSong) {
            loadSong(state.currentSong!!)
        } else {
            nextSong()
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mediaPlayer.start()
        state = state.copy(isPlaying = mediaPlayer.isPlaying)
    }
}