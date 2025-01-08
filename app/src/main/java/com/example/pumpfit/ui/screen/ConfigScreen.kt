package com.example.pumpfit.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ConfigScreen(onClearFavorites: () -> Unit) {
    // basic configuration screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141414)) // Cor de fundo da tela
            .padding(16.dp)
    ) {

        Text(text = "Configurações", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onClearFavorites() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Limpar Favoritos")
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun ConfigScreenPreview() {
//    ConfigScreen()
//}