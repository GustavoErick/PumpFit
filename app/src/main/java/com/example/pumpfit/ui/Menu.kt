package com.example.pumpfit.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun Menu(navController: NavController) {
    var expanded by remember { mutableStateOf(false) } // Controla a abertura do menu

    Box() {
        // Botão dos 3 pontinhos
        IconButton(onClick = { expanded = true }) {
            Icon(
                Icons.Default.MoreVert, // Substitua com seu ícone de 3 pontinhos
                contentDescription = "Menu",
                tint = Color.Gray
            )
        }

        // Dropdown Menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false } // Fecha o menu ao clicar fora
        ) {
            // Itens do menu
            DropdownMenuItem(onClick = {
                expanded = false
                navController.navigate("home")
            }) {
                Text("Tela Inicial")
            }
            DropdownMenuItem(onClick = {
                expanded = false
                navController.navigate("favorites")
            }) {
                Text("Favoritos")
            }
            DropdownMenuItem(onClick = {
                expanded = false
                navController.navigate("home")
            }) {
                Text("Configurações")
            }
            DropdownMenuItem(onClick = {
                expanded = false
                navController.navigate("home")
            }) {
                Text("Ajuda")
            }
            DropdownMenuItem(onClick = {
                expanded = false
                navController.navigate("home")
            }) {
                Text("Logout")
            }
        }
    }
}
