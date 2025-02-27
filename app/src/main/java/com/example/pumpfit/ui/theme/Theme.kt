package com.example.pumpfit.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color.Red,
    secondary = Color(0xFF626262),
    tertiary =Color(0xFFCFCFCF) ,
    background = Color(0xFF141414),
    onBackground = Color(0xFF090909),
    surface = Color(0xFF626262),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF141414),
    secondary = Color(0xFF232222),
    tertiary =Color.Black,
    background = Color.White,
    onBackground = Color(0xFFCFCFCF),
    surface = Color(0xFF9E9D9D),
)

@Composable
fun PumpFitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}