package com.example.pumpfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.pumpfit.model.datastore.SettingsDataStore
import com.example.pumpfit.navigation.MainScreen
import com.example.pumpfit.ui.theme.PumpFitTheme

class MainActivity : ComponentActivity() {
    private val settingsDataStore by lazy { SettingsDataStore(applicationContext) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PumpFitApp(settingsDataStore)
        }
    }
}

@Composable
fun PumpFitApp(settingsDataStore: SettingsDataStore) {
    val userPreference by settingsDataStore.isDarkTheme.collectAsState(initial = null) // Pode ser null se não tiver preferência salva
    val useDeviceTheme by settingsDataStore.useDeviceTheme.collectAsState(initial = true) // Padrão: usar o tema do dispositivo
    val systemTheme = isSystemInDarkTheme() // Verifica o tema do sistema
    val isDarkTheme = if (useDeviceTheme) systemTheme else userPreference ?: false

    PumpFitTheme(darkTheme = isDarkTheme) {
        MainScreen(isDarkTheme = isDarkTheme, settingsDataStore = settingsDataStore)
    }
}