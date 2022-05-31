package com.mk.player_presentation.service

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mk.player_presentation.notification.PlayerNotification
import com.mk.player_presentation.utils.Constants.ACTION_NEXT
import com.mk.player_presentation.utils.Constants.ACTION_PLAY_OR_PAUSE
import com.mk.player_presentation.utils.Constants.ACTION_PREVIOUS
import com.mk.player_presentation.utils.Constants.NOTIFICATION_ID
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MusicService : Service() {
    @Inject
    lateinit var playerNotification: PlayerNotification

    private var isFirstRun = true

    var state by mutableStateOf(MusicServiceState())
        private set

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_PLAY_OR_PAUSE -> {
                    if (isFirstRun) {
                        Log.d("MUSICSERVICE", "CREATED")
                        startService()
                        isFirstRun = false
                    }
                    Log.d("MUSICSERVICE", "PLAY OR PAUSE")
                }
                ACTION_PREVIOUS -> {
                    Log.d("MUSICSERVICE", "PREVIOUS")
                }
                ACTION_NEXT -> {
                    Log.d("MUSICSERVICE", "NEXT")
                }
                else -> {}
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startService() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = playerNotification.createNotification(
            song = state.currentSong,
            context = this,
            notificationManager = notificationManager
        )
        startForeground(NOTIFICATION_ID, notification)
    }

    override fun onDestroy() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(NOTIFICATION_ID)
        super.onDestroy()
    }

    override fun onBind(p0: Intent?) = null
}