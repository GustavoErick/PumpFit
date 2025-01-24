package com.example.pumpfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
    val isDarkTheme by settingsDataStore.isDarkTheme.collectAsState(initial = false)

    PumpFitTheme(darkTheme = isDarkTheme) {
        MainScreen(isDarkTheme = isDarkTheme, settingsDataStore = settingsDataStore)
    }
}