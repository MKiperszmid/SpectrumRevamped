package com.mk.player_presentation.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.mk.core_ui.R
import com.mk.player_domain.model.Song
import com.mk.player_presentation.utils.Constants.NOTIFICATION_CHANNEL_ID
import com.mk.player_presentation.utils.Constants.NOTIFICATION_CHANNEL_NAME

class PlayerNotification {
    fun createNotification(
        song: Song,
        context: Context,
        notificationManager: NotificationManager
    ): Notification {
        createNotificationChannel(notificationManager)
        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.spclogo)
            .setContentTitle(song.title)
            .setContentText(song.artist.name).build()
        //TODO: Replace with Music Notification. Also onClick, navigate to PlayerScreen
    }

    private fun createNotificationChannel(
        notificationManager: NotificationManager
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}