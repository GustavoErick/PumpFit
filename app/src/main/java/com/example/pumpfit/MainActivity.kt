package com.example.pumpfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.pumpfit.model.data.AuthRepository
import com.example.pumpfit.model.datastore.SettingsDataStore
import com.example.pumpfit.model.viewmodels.AuthViewModel
import com.example.pumpfit.navigation.MainScreen
import com.example.pumpfit.ui.theme.PumpFitTheme

class MainActivity : ComponentActivity() {
    private val settingsDataStore by lazy { SettingsDataStore(applicationContext) }
    private val authViewModel by lazy {
        val repository = AuthRepository()
        AuthViewModel(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PumpFitApp(
                settingsDataStore = settingsDataStore,
                authViewModel = authViewModel
            )
        }
    }
}

@Composable
fun PumpFitApp(
    settingsDataStore: SettingsDataStore,
    authViewModel: AuthViewModel
) {
    val isDarkTheme by settingsDataStore.isDarkTheme.collectAsState(initial = false)

    PumpFitTheme(darkTheme = isDarkTheme) {
        MainScreen(
            isDarkTheme = isDarkTheme,
            authViewModel = authViewModel,
            settingsDataStore = settingsDataStore,
        )
    }
}