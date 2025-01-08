package com.example.pumpfit.ui.screen

import androidx.compose.foundation.Image
import com.example.pumpfit.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pumpfit.model.mock.mockMuscleGroups
import com.example.pumpfit.model.mock.mockUsers
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import com.example.pumpfit.model.MuscleGroup

@Composable
fun HomeScreen(onMuscleGroupSelected: (String) -> Unit) {
    val user = mockUsers.find { it.id == "4" }
    val currentHour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
    val greetingMessage = when {
        currentHour in 5..11 -> "Bom dia,"
        currentHour in 12..17 -> "Boa tarde,"
        else -> "Boa noite,"
    }

    var searchQuery by remember { mutableStateOf("") }

    // Filtra os agrupamentos musculares com base na pesquisa
    val filteredMuscleGroups = remember(searchQuery) {
        mockMuscleGroups.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141414)) // Cor de fundo da tela
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = greetingMessage,
                    color = Color(0xFFCFCFCF), // Cor do texto
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = user?.name ?: "Usuário não encontrado",
                    color = Color(0xFFCFCFCF), // Cor do texto
                    style = MaterialTheme.typography.h6
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.FitnessCenter,
                    contentDescription = "Treino",
                    tint = Color.Red,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "05", // Substitua pelo número real de dias
                    color = Color.Red,
                    style = MaterialTheme.typography.h6
                )
            }
        }

        // Barra de Pesquisa
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("O que quer treinar hoje?", color = Color(0xFFCFCFCF)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(30.dp)), // Bordas arredondadas
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(0xFF626262),
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                textColor = Color(0xFFCFCFCF)
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Ícone de busca",
                    tint = Color(0xFFCFCFCF)
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // LazyColumn para exibir os Cards
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(filteredMuscleGroups) { muscleGroup ->
                MuscleGroupCard(
                    muscleGroup = muscleGroup,
                    onClick = { onMuscleGroupSelected(muscleGroup.id) }
                )
            }
        }
    }
}

@Composable
fun MuscleGroupCard(muscleGroup: MuscleGroup, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(Color.Gray)
            .clickable { onClick() } // Navega ao clicar no card
    ) {
        Image(
            painter = painterResource(id = muscleGroup.imageRes),
            contentDescription = "Imagem de ${muscleGroup.name}",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(RoundedCornerShape(30.dp))
                .fillMaxSize()
        )
        Text(
            text = muscleGroup.name,
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .background(Color.Black.copy(alpha = 0.5f), shape = RoundedCornerShape(30.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        onMuscleGroupSelected = { muscleGroupId ->
            // Mock da navegação
            println("Grupo muscular selecionado: $muscleGroupId")
        }
    )
}

