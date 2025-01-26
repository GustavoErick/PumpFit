package com.example.pumpfit.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pumpfit.model.Exercise
import com.example.pumpfit.R
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.example.pumpfit.model.datastore.SettingsDataStore
import com.example.pumpfit.model.viewmodels.ExerciseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun FavoritesScreen(
    favoriteExercises: List<Exercise>,
    onExerciseClick: (String) -> Unit, // Navega para detalhes do exercício
    onFavoriteClick: (Exercise) -> Unit, // Callback para desfavoritar
    onBackClick: () -> Unit, // Callback para voltar
    viewModel: ExerciseViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {


    val context = LocalContext.current
    val settingsDataStore = SettingsDataStore(context)
    var initialFavoriteExercises by remember { mutableStateOf(favoriteExercises) }
    val animationsEnabled = runBlocking { settingsDataStore.visualAnimations.first() }
    var isLoading by remember { mutableStateOf(true) }

    // Simula um atraso buscar os dados
    LaunchedEffect(Unit) {
        delay(1000) // Atraso de 1 segundo
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Favoritos",
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 20.sp // Tamanho ajustado para maior consistência com o restante do app
                    )
                },
                backgroundColor = MaterialTheme.colorScheme.onBackground,
                contentColor = MaterialTheme.colorScheme.tertiary,
                navigationIcon = { // Ícone de voltar
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back), // Use o recurso correto
                            contentDescription = "Voltar",
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if(!isLoading){

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                if (initialFavoriteExercises.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Nenhum exercício favoritado.",
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = 16.sp
                        )
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(initialFavoriteExercises, key = { it.id }) { exercise ->
                            if (animationsEnabled) {
                                var isVisible by remember { mutableStateOf(true) }

                                AnimatedVisibility(
                                    visible = isVisible,
                                    enter = fadeIn() + slideInVertically(),
                                    exit = fadeOut() + slideOutHorizontally(
                                        targetOffsetX = { -it } // Desloca o card para a esquerda
                                    ),
                                ) {
                                    ExerciseCardComposableCLick(
                                        exercise = exercise,
                                        isFavorite = true,
                                        onFavoriteClick = {
                                            // Define como invisível antes de remover o item
                                            isVisible = false
                                            /*onFavoriteClick(exercise)*/
                                        },
                                        onClick = { onExerciseClick(exercise.id) },
                                    )
                                }
                                if (!isVisible) {
                                    LaunchedEffect(Unit) {
                                        kotlinx.coroutines.delay(300) // Tempo da animação
                                        initialFavoriteExercises = initialFavoriteExercises.filter { it.id != exercise.id }
                                        onFavoriteClick(exercise)
                                    }
                                }
                            } else {
                                ExerciseCard(
                                    exercise = exercise,
                                    isFavorite = true,
                                    onFavoriteClick = onFavoriteClick,
                                    onClick = { onExerciseClick(exercise.id) }
                                )
                            }
                        }
                    }
                }
            }
        } else {
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
                    androidx.compose.material.Text(
                        text = "Buscando...",
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ExerciseCardComposableCLick(
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
                androidx.compose.material.Text(
                    text = exercise.name,
                    color = Color(0xFFCFCFCF),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                androidx.compose.material.Text(
                    text = exercise.description,
                    color = Color(0xFFCFCFCF),
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            androidx.compose.material.IconButton(onClick = {
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


