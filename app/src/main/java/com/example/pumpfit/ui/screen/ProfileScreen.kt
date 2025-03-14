package com.example.pumpfit.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pumpfit.R
import com.example.pumpfit.model.mock.mockUsers
import com.example.pumpfit.model.viewmodels.AuthViewModel


@Composable
fun ProfileScreen(userId: String, onBackClick: () -> Unit, authViewModel: AuthViewModel) {
    val user = mockUsers.find { it.id == userId }
    var userName by remember { mutableStateOf<String?>(null) }


    LaunchedEffect(Unit) {
        authViewModel.getUserName { name ->
            userName = name ?: "Usuário não encontrado"
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Perfil", color = MaterialTheme.colorScheme.tertiary) },
                backgroundColor = MaterialTheme.colorScheme.onBackground,
                contentColor = MaterialTheme.colorScheme.tertiary,
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Voltar",
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = user?.image ?: R.drawable.ic_profile_placeholder),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = userName ?: "Carregando...",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                backgroundColor = MaterialTheme.colorScheme.onBackground,
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ProfileAttribute(
                        icon = R.drawable.ic_weight,
                        value = user?.weight?.toString() ?: "-",
                        unit = "quilos"
                    )
                    ProfileAttribute(
                        icon = R.drawable.ic_height,
                        value = user?.height?.toString() ?: "-",
                        unit = "metros"
                    )
                    ProfileAttribute(
                        icon = R.drawable.ic_goal,
                        value = user?.period ?: "Indefinido",
                        unit = ""
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileAttribute(icon: Int, value: String, unit: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
            if (unit.isNotEmpty()) {
                Text(
                    text = unit,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}
