package com.mk.player_presentation.notification

import android.app.*
import android.app.NotificationManager.IMPORTANCE_LOW
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.mk.core_ui.R
import com.mk.player_domain.model.Song
import com.mk.player_presentation.utils.Constants.NOTIFICATION_CHANNEL_ID
import com.mk.player_presentation.utils.Constants.NOTIFICATION_CHANNEL_NAME

class PlayerNotification {
    fun createNotification(
        context: Context,
        session: MediaSessionCompat,
        notificationManager: NotificationManager
    ): Notification {
        createNotificationChannel(notificationManager)
        //TODO: Investigate about Media2 and Media3 libraries

        val controller = session.controller
        val metadata = controller.metadata
        val description = metadata.description
        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID).apply {
            setSmallIcon(R.drawable.spclogo)
            setContentTitle(description.title)
            setContentText(description.subtitle)
            setSubText(description.description)
            setLargeIcon(description.iconBitmap)
            setContentIntent(controller.sessionActivity)
            priority = NotificationCompat.PRIORITY_LOW
            addAction(com.mk.player_presentation.R.drawable.ic_previous, "previous", null)
            //.setContentIntent(getPendingIntent(context)) TODO: Fix this. Stops playing the current song
            setStyle(
                androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(0)
                    .setMediaSession(session.sessionToken)
            )
            setAutoCancel(false)
            setOngoing(true)
        }.build()

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