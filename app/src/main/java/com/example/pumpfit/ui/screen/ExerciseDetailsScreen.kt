package com.example.pumpfit.ui.screen

import android.widget.VideoView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.pumpfit.R
import com.example.pumpfit.model.Exercise
import com.example.pumpfit.model.mock.mockExercises
import com.example.pumpfit.util.startTimer
import com.example.pumpfit.model.datastore.SettingsDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun ExerciseDetailsScreen(exercise: Exercise, onBackClick: () -> Unit) {
    var isPlaying by remember { mutableStateOf(false) } // Controla se o vídeo está sendo reproduzido
    var timeRemaining by remember { mutableStateOf(0L) } // Tempo restante em milissegundos
    val context = LocalContext.current
    val videoProgress = remember { mutableStateOf(0f) }
    var videoView: VideoView? by remember { mutableStateOf(null) }
    val settingsDataStore = SettingsDataStore(context)
    val animationationsEnabled = runBlocking { settingsDataStore.visualAnimations.first() }

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
            if (!isPlaying) {
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
                        onClick = { isPlaying = true }, // Inicia o vídeo
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
            } else {
                AndroidView(
                    factory = { context ->
                        VideoView(context).apply {
                            setVideoPath("android.resource://${context.packageName}/${exercise.video}")
                            setOnPreparedListener { it.start() } // Inicia o vídeo
                            setOnCompletionListener {
                                isPlaying = false
                                videoProgress.value = 0f
                            }
                            videoView = this
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
            //ProgressBar para o vídeo
            if(isPlaying && animationationsEnabled) {
                LinearProgressIndicator(
                    progress = videoProgress.value,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = exercise.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Spacer(modifier = Modifier.height(4.dp))
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
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
            ) {
                exercise.machines.forEach { machine ->
                    Chip(text = machine)
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

            Spacer(modifier = Modifier.height(32.dp))

            if (timeRemaining > 0) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = formatMillisToMinutesAndSeconds(timeRemaining),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
            } else if (timeRemaining == 0L) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "00:00",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão para iniciar o temporizador
            Button (
                onClick = {
                    val intervalMillis = parseIntervalToMillis(exercise.interval)
                    timeRemaining = intervalMillis
                    startTimer(context, intervalMillis)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Iniciar Temporizador",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        // Atualiza o temporizador
        LaunchedEffect(timeRemaining) {
            if (timeRemaining > 0) {
                kotlinx.coroutines.delay(1000L)
                timeRemaining -= 1000L
            }
        }

        // Atualiza o progresso do vídeo
        LaunchedEffect(isPlaying) {
            while (isPlaying && videoView != null) {
                videoView?.let {
                    val duration = it.duration
                    val currentPosition = it.currentPosition
                    videoProgress.value = currentPosition.toFloat() / duration.toFloat()
                }
                kotlinx.coroutines.delay(200L) // Atualiza a cada 200ms
            }
        }
    }
}

@Composable
fun DetailsRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
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

// Formata milissegundos para MM:SS
fun formatMillisToMinutesAndSeconds(millis: Long): String {
    val totalSeconds = millis / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}

fun parseIntervalToMillis(interval: String): Long {
    val parts = interval.split(":").map { it.toIntOrNull() ?: 0 }
    val minutes = parts.getOrNull(0) ?: 0
    val seconds = parts.getOrNull(1) ?: 0
    return (minutes * 60 + seconds) * 1000L
}

@Preview(showBackground = true)
@Composable
fun PreviewExerciseDetailsScreen() {
    val exercise = mockExercises.find { it.id == "1" }
    exercise?.let {
        ExerciseDetailsScreen(exercise = it, onBackClick = {})
    }
}