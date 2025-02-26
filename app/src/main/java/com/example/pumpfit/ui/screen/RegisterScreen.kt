package com.example.pumpfit.ui.screen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pumpfit.model.datastore.SettingsDataStore
import com.example.pumpfit.model.viewmodels.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    navController: NavController,
    settingsDataStore: SettingsDataStore
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        isVisible = true // Ativa animação ao entrar na tela
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically() + fadeIn()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Criar Conta", fontSize = 30.sp, style = MaterialTheme.typography.headlineLarge)

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = "Nome",
                    icon = Icons.Filled.Person
                )
                CustomTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    icon = Icons.Filled.Email
                )

                CustomTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Senha",
                    icon = Icons.Filled.Lock,
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(16.dp))

//                Button(
//                    onClick = {
//                        viewModel.register(email, password, name) { success ->
//                            if (success) {
//                                // Salva no DataStore que o usuário está logado
//                                scope.launch {
//                                    settingsDataStore.setUserLoggedIn(true)
//                                }
//                                navController.navigate("home") {
//                                    popUpTo("register") { inclusive = true }
//                                }
//                            } else {
//                                Toast.makeText(context, "Erro no cadastro", Toast.LENGTH_LONG).show()
//                            }
//                        }
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Text("Registrar", fontSize = 18.sp)
//                }
                Button(
                    onClick = {
                        viewModel.register(email, password, name) { success, message ->
                            if (success) {
                                scope.launch {
                                    settingsDataStore.setUserLoggedIn(true)
                                }

                                // Exibir mensagem de sucesso
                                Toast.makeText(context, message ?: "Conta criada com sucesso!", Toast.LENGTH_LONG).show()

                                // Redirecionar para Home
                                navController.navigate("home") {
                                    popUpTo("register") { inclusive = true }
                                }
                            } else {
                                // Exibir mensagem de erro
                                Toast.makeText(context, message ?: "Erro ao cadastrar", Toast.LENGTH_LONG).show()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Registrar", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(onClick = { navController.navigate("login") }) {
                    Text("Já tem uma conta? Faça login")
                }
            }
        }
    }
}
