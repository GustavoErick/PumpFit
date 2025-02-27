package com.example.pumpfit.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pumpfit.R
import com.example.pumpfit.model.datastore.SettingsDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigScreen(
    onBackClick: () -> Unit,
    onClearFavorites: () -> Unit,
    settingsDataStore: SettingsDataStore,
    scope: CoroutineScope
) {
    val isDarkTheme = settingsDataStore.isDarkTheme.collectAsState(initial = false).value
    val notificationsEnabled = settingsDataStore.notificationsEnabled.collectAsState(initial = true).value
    val visualAnimations = settingsDataStore.visualAnimations.collectAsState(initial = true).value
    val useDeviceTheme = settingsDataStore.useDeviceTheme.collectAsState(initial = true).value
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Configurações",
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Voltar",
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onBackground,
                    titleContentColor = MaterialTheme.colorScheme.tertiary,
                    navigationIconContentColor = MaterialTheme.colorScheme.tertiary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { onClearFavorites() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Limpar Favoritos", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Usar tema do sistema",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )

                Checkbox(
                    checked = useDeviceTheme,
                    onCheckedChange = { isChecked ->
                        scope.launch {
                            settingsDataStore.setUseDeviceTheme(isChecked)
                        }
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Red,
                        uncheckedColor = Color.Gray
                    )
                )
            }

            if (!useDeviceTheme) {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Tema Escuro",
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )

                    Switch(
                        checked = isDarkTheme,
                        onCheckedChange = { isChecked ->
                            scope.launch {
                                settingsDataStore.setDarkTheme(isChecked)
                            }
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.Red,
                            uncheckedThumbColor = Color.Gray,
                            checkedTrackColor = Color(0xFFB71C1C),
                            uncheckedTrackColor = Color(0xFFB0BEC5)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Notificações",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )

                Switch(
                    checked = notificationsEnabled,
                    onCheckedChange = { isChecked ->
                        scope.launch {
                            settingsDataStore.setNotificationsEnabled(isChecked)
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.Red,
                        uncheckedThumbColor = Color.Gray,
                        checkedTrackColor = Color(0xFFB71C1C),
                        uncheckedTrackColor = Color(0xFFB0BEC5)
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Animações",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )

                Switch(
                    checked = visualAnimations,
                    onCheckedChange = { isChecked ->
                        scope.launch {
                            settingsDataStore.setVisualAnimations(isChecked)
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.Red,
                        uncheckedThumbColor = Color.Gray,
                        checkedTrackColor = Color(0xFFB71C1C),
                        uncheckedTrackColor = Color(0xFFB0BEC5)
                    )
                )
            }
        }
    }
}



