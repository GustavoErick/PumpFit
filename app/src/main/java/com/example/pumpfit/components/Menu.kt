//package com.example.pumpfit.components
//
//import androidx.compose.foundation.layout.Box
//import androidx.compose.material.DropdownMenu
//import androidx.compose.material.DropdownMenuItem
//import androidx.compose.material3.Icon
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.IconButton
//import androidx.compose.material.Text
//import androidx.compose.material.icons.filled.MoreVert
//import androidx.compose.runtime.*
//import androidx.compose.ui.graphics.Color
//import androidx.navigation.NavController
//
//@Composable
//fun Menu(navController: NavController) {
//    var expanded by remember { mutableStateOf(false) } // Controla a abertura do menu
//
//    Box() {
//        // Botão dos 3 pontinhos
//        IconButton(onClick = { expanded = true }) {
//            Icon(
//                Icons.Default.MoreVert, // Substitua com seu ícone de 3 pontinhos
//                contentDescription = "Menu",
//                tint = Color.Gray
//            )
//        }
//
//        // Dropdown Menu
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false } // Fecha o menu ao clicar fora
//        ) {
//            // Itens do menu
//            DropdownMenuItem(onClick = {
//                expanded = false
//                navController.navigate("home", {
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                })
//            }) {
//                Text("Tela Inicial")
//            }
//            DropdownMenuItem(onClick = {
//                expanded = false
//                navController.navigate("favorites"){
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }) {
//                Text("Favoritos")
//            }
//            DropdownMenuItem(onClick = {
//                expanded = false
//                navController.navigate("settings"){
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }) {
//                Text("Configurações")
//            }
//            DropdownMenuItem(onClick = {
//                expanded = false
//                navController.navigate("home"){
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }) {
//                Text("Ajuda")
//            }
//            DropdownMenuItem(onClick = {
//                expanded = false
//                navController.navigate("home"){
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }) {
//                Text("Logout")
//            }
//        }
//    }
//}
package com.example.pumpfit.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Menu(navController: NavController) {
    var expanded by remember { mutableStateOf(false) } // Controla a abertura do menu

    Box {
        // Botão dos 3 pontinhos
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "Menu",
                tint = Color.White // Deixe consistente com o tema geral do app
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
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                Text(
                    text = "Tela Inicial",
                    fontSize = 16.sp,
                    color = Color.Black // Ajuste de cor para contraste
                )
            }
            DropdownMenuItem(onClick = {
                expanded = false
                navController.navigate("favorites") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                Text(
                    text = "Favoritos",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            DropdownMenuItem(onClick = {
                expanded = false
                navController.navigate("settings") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                Text(
                    text = "Configurações",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            DropdownMenuItem(onClick = {
                expanded = false
                navController.navigate("help") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                Text(
                    text = "Ajuda",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            DropdownMenuItem(onClick = {
                expanded = false
                // Lógica para logout aqui
                navController.navigate("login") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            }) {
                Text(
                    text = "Logout",
                    fontSize = 16.sp,
                    color = Color.Red // Destacar logout em vermelho
                )
            }
        }
    }
}
