package com.example.pumpfit.util

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import com.example.pumpfit.model.datastore.SettingsDataStore
import com.example.pumpfit.services.TimerReceiver
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

fun startTimer(context: Context, durationMillis: Long) {
    try {
        val settingsDataStore = SettingsDataStore(context)
        val notificationsEnabled = runBlocking { settingsDataStore.notificationsEnabled.first() }

        if (!notificationsEnabled) {
            Log.i("Timer", "Notificações estão desativadas. Nenhum alarme será configurado.")
            return
        }

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Verifica permissões de alarme exato em Android 12+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
            // Redireciona para a tela de permissões
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
            return
        }

        // Configura o intent para o TimerReceiver
        val intent = Intent(context, TimerReceiver::class.java).apply {
            action = "TIMER_ACTION"
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + durationMillis,
            pendingIntent
        )
        Log.i("Timer", "Alarme configurado para ${durationMillis / 1000} segundos.")
    } catch (e: SecurityException) {
        Log.e("TimerError", "Permission not granted: ${e.message}")
    } catch (e: Exception) {
        Log.e("TimerError", "Unexpected error: ${e.message}")
    }
}

// Cria o canal de notificações
fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            "timer_channel",
            "Timer Notifications",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Notificações de intervalos de exercícios"
        }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
