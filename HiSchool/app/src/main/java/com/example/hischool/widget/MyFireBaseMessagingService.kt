package com.example.hischool.widget

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.hischool.R
import com.example.hischool.view.activity.LoadingActivity
import com.example.hischool.view.activity.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFireBaseMessagingService : FirebaseMessagingService() {

    //FCM 토큰발급
    override fun onNewToken(s: String) {
        Log.d("FCM Log", "Refreshed token: $s")
    }

    //메세지 들어온거 받아서 띄움
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("FCM Log", "알림 메세지: " + remoteMessage.data)

        if(remoteMessage.notification != null)
        {
            sendNotification(remoteMessage.notification!!.body.toString(), remoteMessage.notification!!.title.toString())
        }
        else if(remoteMessage.data.isNotEmpty())
        {
            sendNotification(remoteMessage.data["text"].toString(), remoteMessage.data["title"].toString())
        }
    }

    private fun sendNotification(messageBody : String, messageTitle : String){
        val intent = Intent(this, LoadingActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val channelId = "Channel ID"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Channel Name"
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder.build())

    }
}