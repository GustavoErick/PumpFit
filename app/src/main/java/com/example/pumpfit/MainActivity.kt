package com.example.pumpfit


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.pumpfit.ui.MainScreen
import com.example.pumpfit.ui.screen.HomeScreen

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
    MainScreen()
}
