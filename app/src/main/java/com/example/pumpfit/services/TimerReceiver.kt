package com.example.pumpfit.services

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.pumpfit.R
import com.example.pumpfit.util.createNotificationChannel

class TimerReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            createNotificationChannel(it)

            val notificationManager =
                it.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val notification = NotificationCompat.Builder(it, "timer_channel")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("PumpFit")
                .setContentText("Intervalo finalizado! Hora de iniciar a próxima série.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .build()

            notificationManager.notify(System.currentTimeMillis().toInt(), notification)
        }
    }
}
