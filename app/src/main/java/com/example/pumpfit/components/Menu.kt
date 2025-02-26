//package com.example.pumpfit.components
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.material.DropdownMenu
//import androidx.compose.material.DropdownMenuItem
//import androidx.compose.material3.Icon
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.IconButton
//import androidx.compose.material.Text
//import androidx.compose.material.icons.filled.MoreVert
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//
//@Composable
//fun Menu(navController: NavController) {
//    var expanded by remember { mutableStateOf(false) } // Controla a abertura do menu
//
//    Box() {
//        // Botão dos 3 pontinhos
//        IconButton(onClick = { expanded = !expanded }) {
//            Icon(
//                Icons.Default.MoreVert,
//                contentDescription = "Menu",
//                tint = MaterialTheme.colorScheme.tertiary
//            )
//        }
//
//        // Dropdown Menu
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }, // Fecha o menu ao clicar fora
//            modifier = Modifier
//                .background(
//                    color = MaterialTheme.colorScheme.onBackground, // Cor de fundo do menu
//                )
//        ) {
//            // Itens do menu
//            DropdownMenuItem(onClick = {
//                expanded = false
//                navController.navigate("home") {
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }) {
//                Text("Tela Inicial", fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
//            }
//            DropdownMenuItem(onClick = {
//                expanded = false
//                navController.navigate("favorites") {
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }) {
//                Text("Favoritos", fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
//            }
//            DropdownMenuItem(onClick = {
//                expanded = false
//                navController.navigate("settings") {
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }) {
//                Text("Configurações", fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
//            }
//            DropdownMenuItem(onClick = {
//                expanded = false
//                navController.navigate("help") {
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }) {
//                Text("Ajuda", fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
//            }
//            DropdownMenuItem(onClick = {
//                expanded = false
//                // Lógica para logout aqui
//                navController.navigate("login") {
//                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
//                }
//            }) {
//                Text("Logout", fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
//            }
//        }
//    }
//}

//package com.example.pumpfit.components
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.material.DropdownMenu
//import androidx.compose.material.DropdownMenuItem
//import androidx.compose.material3.Icon
//import androidx.compose.material.IconButton
//import androidx.compose.material.Text
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.MoreVert
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import com.example.pumpfit.model.data.AuthRepository
//import com.example.pumpfit.model.viewmodels.AuthViewModel
//import com.example.pumpfit.model.viewmodels.AuthViewModelFactory
//import kotlinx.coroutines.launch
//
//@Composable
//fun Menu(navController: NavController, authRepository: AuthRepository) {
//    var expanded by remember { mutableStateOf(false) } // Controla a abertura do menu
//    val coroutineScope = rememberCoroutineScope()
//
//    val authViewModel: AuthViewModel = viewModel(
//        factory = AuthViewModelFactory(authRepository)
//    )
//
//    Box {
//        // Botão dos 3 pontinhos
//        IconButton(onClick = { expanded = !expanded }) {
//            Icon(
//                Icons.Default.MoreVert,
//                contentDescription = "Menu",
//                tint = MaterialTheme.colorScheme.tertiary
//            )
//        }
//
//        // Dropdown Menu
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }, // Fecha o menu ao clicar fora
//            modifier = Modifier.background(MaterialTheme.colorScheme.onBackground) // Cor de fundo do menu
//        ) {
//            // Itens do menu
//            DropdownMenuItem(onClick = {
//                expanded = false
//                navController.navigate("home") {
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }) {
//                Text("Tela Inicial", fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
//            }
//
//            DropdownMenuItem(onClick = {
//                expanded = false
//                navController.navigate("favorites") {
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }) {
//                Text("Favoritos", fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
//            }
//
//            DropdownMenuItem(onClick = {
//                expanded = false
//                navController.navigate("settings") {
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }) {
//                Text("Configurações", fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
//            }
//
//            DropdownMenuItem(onClick = {
//                expanded = false
//                navController.navigate("help") {
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }) {
//                Text("Ajuda", fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
//            }
//
//            DropdownMenuItem(onClick = {
//                expanded = false
//                coroutineScope.launch {
//                    authViewModel.logout() // Executa a função de logout
//                    navController.navigate("login") {
//                        popUpTo("home") { inclusive = true } // Limpa o histórico de navegação
//                    }
//                }
//            }) {
//                Text("Logout", fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
//            }
//        }
//    }
//}

package com.example.pumpfit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pumpfit.model.viewmodels.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun Menu(navController: NavController, authViewModel: AuthViewModel) {
    var expanded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Box {
        // Botão dos 3 pontinhos
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "Menu",
                tint = MaterialTheme.colorScheme.tertiary
            )
        }

        // Dropdown Menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(MaterialTheme.colorScheme.onBackground)
        ) {
            DropdownMenuItem(onClick = {
                expanded = false
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                Text("Tela Inicial", fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
            }

            DropdownMenuItem(onClick = {
                expanded = false
                navController.navigate("favorites") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                Text("Favoritos", fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
            }

            DropdownMenuItem(onClick = {
                expanded = false
                navController.navigate("settings") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                Text("Configurações", fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
            }

            DropdownMenuItem(onClick = {
                expanded = false
                navController.navigate("help") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                Text("Ajuda", fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
            }

            // ✅ Correção no Logout: usa o AuthViewModel e volta ao login da MainNavigation
            DropdownMenuItem(onClick = {
                expanded = false
                coroutineScope.launch {
                    authViewModel.logout() // Faz logout
                    navController.navigate("login") {
                        popUpTo("main") { inclusive = true } // Remove o histórico de navegação
                    }
                }
            }) {
                Text("Logout", fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
            }
        }
    }
}