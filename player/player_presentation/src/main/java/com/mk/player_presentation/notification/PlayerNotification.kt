package com.mk.player_presentation.notification

import android.app.*
import android.app.NotificationManager.IMPORTANCE_LOW
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
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
            .setContentText(song.artist.name)
            .setContentIntent(getPendingIntent(context)).build()
        //TODO: Replace with Music Notification. Also onClick, navigate to PlayerScreen
    }

    private fun getPendingIntent(context: Context): PendingIntent {
        //TODO: Investigate if this is the best way to do it
        val intent = Intent(Intent.ACTION_VIEW, "https://www.spectrumrevamped.com/player".toUri())
        return TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT)
        }
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