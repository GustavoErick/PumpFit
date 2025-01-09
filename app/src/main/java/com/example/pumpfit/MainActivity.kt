package com.example.pumpfit


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.pumpfit.navigation.MainScreen
import com.example.pumpfit.ui.theme.PumpFitTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PumpFitApp()
        }
    }
}


@Composable
fun PumpFitApp() {
    val isDarkTheme = remember { mutableStateOf(true) }
    PumpFitTheme(
        darkTheme = isDarkTheme.value,
        dynamicColor = false
    ) {
        MainScreen(isDarkTheme = isDarkTheme)
    }
}

