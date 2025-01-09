package com.example.pumpfit.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pumpfit.R
import com.example.pumpfit.model.Exercise
import com.example.pumpfit.model.mock.mockExercises

@Composable
fun ExerciseDetailsScreen(exercise: Exercise, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = exercise.name, color = MaterialTheme.colorScheme.tertiary) },
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
                .padding(16.dp)
        ) {
            // Imagem com botão de vídeo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Red), // Placeholder vermelho para imagem
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = exercise.image),
                    contentDescription = "Imagem do exercício",
                    modifier = Modifier.fillMaxSize()
                )
                IconButton(
                    onClick = { /* Reproduzir vídeo */ },
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color(0xFF090909), shape = CircleShape),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_play),
                        contentDescription = "Reproduzir vídeo",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nome do exercício e grupo muscular centralizados
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally // Centralização horizontal
            ) {
                Text(
                    text = exercise.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Spacer(modifier = Modifier.height(4.dp)) // Espaçamento entre os textos
                Text(
                    text = exercise.muscleGroup,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tags de Máquinas Utilizadas
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally) // Espaçamento centralizado
            ) {
                exercise.machines.forEach { machine ->
                    Chip(text = machine) // Exibe o nome de cada máquina como um Chip
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Detalhes do exercício
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                DetailsRow(label = "Carga", value = exercise.weight)
                DetailsRow(label = "Séries", value = exercise.sets)
                DetailsRow(label = "Metodologia", value = exercise.methodology)
                DetailsRow(label = "Intervalo", value = exercise.interval)
            }
        }
    }
}

@Composable
fun DetailsRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), // Espaçamento vertical para cada atributo
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.tertiary)
        Text(text = value, fontSize = 14.sp, color = MaterialTheme.colorScheme.tertiary)
    }
}

@Composable
fun Chip(text: String) {
    Box(
        modifier = Modifier
            .background(Color.Red, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text = text, color = Color.White, fontSize = 12.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExerciseDetailsScreen() {
    val exercise = mockExercises.find { it.id == "1" } // Usa o ID para pegar o exercício correto do mock
    exercise?.let {
        ExerciseDetailsScreen(exercise = it, onBackClick = {})
    }
}



