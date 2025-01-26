package com.example.pumpfit.ui.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pumpfit.R
import com.example.pumpfit.model.Exercise
import com.example.pumpfit.model.datastore.SettingsDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


@Composable
fun ExerciseListScreen(
    muscleGroup: String,
    exercises: List<Exercise>,
    favoriteExercises: List<Exercise>, // Lista de exercícios favoritos
    onExerciseClick: (String) -> Unit, // Callback para clicar no exercício
    onFavoriteClick: (Exercise) -> Unit, // Callback para adicionar/remover favoritos
    onBackClick: () -> Unit // Callback para o botão de voltar
) {
    var isLoading by remember { mutableStateOf(true) }


    // Simula um atraso buscar os dados
    LaunchedEffect(Unit) {
        delay(1000) // Atraso de 1 segundo
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = muscleGroup, color = MaterialTheme.colorScheme.tertiary) },
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
        if (isLoading) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
                    .padding(16.dp)
                ){
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Buscando...",
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(exercises) { exercise ->
                        ExerciseCard(
                            exercise = exercise,
                            isFavorite = favoriteExercises.contains(exercise), // Verifica se está favoritado
                            onFavoriteClick = { onFavoriteClick(exercise) }, // Callback para favoritar/desfavoritar
                            onClick = { onExerciseClick(exercise.id) } // Navegação pelo ID do exercício
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ExerciseCard(
    exercise: Exercise,
    isFavorite: Boolean,
    onFavoriteClick: (Exercise) -> Unit, // Callback para clicar no favorito
    onClick: () -> Unit // Callback para clicar no card
) {
    var isAnimating by remember { mutableStateOf(false) } // Estado para controlar a animação

    val context = LocalContext.current
    val settingsDataStore = SettingsDataStore(context)
    val animationsEnabled = runBlocking { settingsDataStore.visualAnimations.first() }

    // Animação de escala para o ícone
    val scale by animateFloatAsState(
        targetValue = if (isAnimating) 2f else 1f,
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 150),
        finishedListener = { isAnimating = false } // Reseta após a animação
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick), // Clique no card navega para detalhes
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color(0xFF626262),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagem no formato crop com bordas arredondadas
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp)) // Borda arredondada
            ) {
                Image(
                    painter = painterResource(id = exercise.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop, // Crop da imagem
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = exercise.name,
                    color = Color(0xFFCFCFCF),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = exercise.description,
                    color = Color(0xFFCFCFCF),
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = {
                isAnimating = true
                onFavoriteClick(exercise)
            }) {
                if (animationsEnabled) {
                    Icon(
                        painter = if (isFavorite) painterResource(R.drawable.ic_favorite) else painterResource(R.drawable.ic_favorite_border),
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color(0xFFCFCFCF),
                        modifier = Modifier.scale(scale) // Aplica a escala ao ícone
                    )
                }else {
                    Icon(
                        painter = if (isFavorite) painterResource(R.drawable.ic_favorite) else painterResource(R.drawable.ic_favorite_border),
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color(0xFFCFCFCF)
                    )
                }
            }
        }
    }
}

