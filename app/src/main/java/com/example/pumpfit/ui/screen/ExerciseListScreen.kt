package com.example.pumpfit.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pumpfit.R
import com.example.pumpfit.model.Exercise
import com.example.pumpfit.model.mock.mockExercises

//@Composable
//fun ExerciseListScreen(
//    muscleGroup: String,
//    exercises: List<Exercise>,
//    onExerciseClick: (Exercise) -> Unit,
//    onBackClick: () -> Unit
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = muscleGroup, color = Color(0xFFCFCFCF)) },
//                backgroundColor = Color(0xFF090909),
//                contentColor = Color(0xFFCFCFCF),
//                navigationIcon = {
//                    IconButton(onClick = { onBackClick() }) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.ic_back),
//                            contentDescription = "Voltar",
//                            tint = Color(0xFFCFCFCF)
//                        )
//                    }
//                }
//            )
//        }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0xFF141414))
//                .padding(innerPadding)
//                .padding(16.dp)
//        ) {
//            LazyColumn(
//                verticalArrangement = Arrangement.spacedBy(16.dp),
//                modifier = Modifier.fillMaxSize()
//            ) {
//                items(exercises) { exercise ->
//                    ExerciseCard(exercise = exercise, onClick = { onExerciseClick(exercise) })
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ExerciseCard(exercise: Exercise, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(100.dp) // Altura maior para mais espaço no texto
//            .clickable { onClick() },
//        shape = RoundedCornerShape(16.dp),
//        backgroundColor = Color(0xFF626262) // Cor do card
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            // Foto do Exercício
//            Image(
//                painter = painterResource(id = exercise.image),
//                contentDescription = "Imagem de ${exercise.name}",
//                contentScale = ContentScale.Crop, // Ajusta a escala da imagem
//                modifier = Modifier
//                    .size(64.dp) // Aumentei o tamanho da imagem
//                    .clip(RoundedCornerShape(8.dp)) // Bordas arredondadas
//            )
//
//            Spacer(modifier = Modifier.width(16.dp))
//
//            // Informações do Exercício
//            Column(
//                verticalArrangement = Arrangement.SpaceEvenly,
//                modifier = Modifier.fillMaxHeight()
//            ) {
//                Text(
//                    text = exercise.name,
//                    color = Color(0xFFCFCFCF),
//                    fontSize = 16.sp,
//                    style = MaterialTheme.typography.subtitle1
//                )
//                Text(
//                    text = exercise.description,
//                    color = Color(0xFFCFCFCF),
//                    fontSize = 14.sp,
//                    style = MaterialTheme.typography.body2,
//                    maxLines = 2, // Limita a 2 linhas
//                    overflow = TextOverflow.Ellipsis // Mostra "..." se o texto for muito longo
//                )
//            }
//        }
//    }
//}

@Composable
fun ExerciseListScreen(
    muscleGroup: String,
    exercises: List<Exercise>,
    onExerciseClick: (String) -> Unit, // Callback para a navegação
    onBackClick: () -> Unit // Callback para o botão de voltar
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = muscleGroup, color = Color(0xFFCFCFCF)) },
                backgroundColor = Color(0xFF090909),
                contentColor = Color(0xFFCFCFCF),
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Voltar",
                            tint = Color(0xFFCFCFCF)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF141414))
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(exercises) { exercise ->
                    ExerciseCard(
                        exercise = exercise,
                        onClick = { onExerciseClick(exercise.id) } // Navegação pelo ID do exercício
                    )
                }
            }
        }
    }
}

//@Composable
//fun ExerciseListScreen(
//    muscleGroup: String,
//    exercises: List<Exercise>,
//    favoriteExercises: List<Exercise>,
//    onExerciseClick: (String) -> Unit,
//    onFavoriteClick: (Exercise) -> Unit,
//    onBackClick: () -> Unit
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = muscleGroup, color = Color(0xFFCFCFCF)) },
//                backgroundColor = Color(0xFF090909),
//                contentColor = Color(0xFFCFCFCF),
//                navigationIcon = {
//                    IconButton(onClick = { onBackClick() }) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.ic_back),
//                            contentDescription = "Voltar",
//                            tint = Color(0xFFCFCFCF)
//                        )
//                    }
//                }
//            )
//        }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0xFF141414))
//                .padding(innerPadding)
//                .padding(16.dp)
//        ) {
//            LazyColumn(
//                verticalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                items(exercises) { exercise ->
//                    ExerciseCard(
//                        exercise = exercise,
//                        isFavorite = favoriteExercises.contains(exercise), // Passa se é favorito
//                        onFavoriteClick = onFavoriteClick, // Callback para favoritar/desfavoritar
//                        onClick = { onExerciseClick(exercise.id) } // Navega para detalhes
//                    )
//                }
//            }
//        }
//    }
//}

//@Composable
//fun ExerciseListScreen(
//    muscleGroup: String,
//    exercises: List<Exercise>,
//    favoriteExercises: List<Exercise>,
//    onExerciseClick: (String) -> Unit,
//    onFavoriteClick: (Exercise) -> Unit
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = muscleGroup, color = Color(0xFFCFCFCF)) },
//                backgroundColor = Color(0xFF090909),
//                contentColor = Color(0xFFCFCFCF),
//                navigationIcon = {
//                    IconButton(onClick = { /* Handle back navigation */ }) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.ic_back),
//                            contentDescription = "Voltar",
//                            tint = Color(0xFFCFCFCF)
//                        )
//                    }
//                }
//            )
//        }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0xFF141414))
//                .padding(innerPadding)
//                .padding(16.dp)
//        ) {
//            LazyColumn(
//                verticalArrangement = Arrangement.spacedBy(16.dp),
//                modifier = Modifier.fillMaxSize()
//            ) {
//                items(exercises) { exercise ->
//                    ExerciseCard(
//                        exercise = exercise,
//                        isFavorite = favoriteExercises.contains(exercise),
//                        onClick = { onExerciseClick(exercise.id) },
//                        onFavoriteClick = { onFavoriteClick(exercise) }
//                    )
//                }
//            }
//        }
//    }
//}



//@Composable
//fun ExerciseCard(
//    exercise: Exercise,
//    onClick: () -> Unit
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable(onClick = onClick), // Clique no card navega para detalhes
//        shape = RoundedCornerShape(16.dp),
//        backgroundColor = Color(0xFF626262),
//        elevation = 4.dp
//    ) {
//        Row(
//            modifier = Modifier.padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                painter = painterResource(id = exercise.image),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(60.dp)
//                    .clip(RoundedCornerShape(8.dp))
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Column {
//                Text(
//                    text = exercise.name,
//                    color = Color(0xFFCFCFCF),
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = exercise.description,
//                    color = Color(0xFFCFCFCF),
//                    fontSize = 12.sp
//                )
//            }
//        }
//    }
//}
@Composable
fun ExerciseCard(
    exercise: Exercise,
    onClick: () -> Unit
) {
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
            Column {
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
        }
    }
}


//@Composable
//fun ExerciseCard(
//    exercise: Exercise,
//    isFavorite: Boolean,
//    onFavoriteClick: (Exercise) -> Unit,
//    onClick: () -> Unit
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable(onClick = onClick), // Clique no card navega para detalhes
//        shape = RoundedCornerShape(16.dp),
//        backgroundColor = Color(0xFF626262),
//        elevation = 4.dp
//    ) {
//        Row(
//            modifier = Modifier.padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            // Imagem no formato crop com bordas arredondadas
//            Box(
//                modifier = Modifier
//                    .size(60.dp)
//                    .clip(RoundedCornerShape(12.dp)) // Borda arredondada
//            ) {
//                Image(
//                    painter = painterResource(id = exercise.image),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop, // Crop da imagem
//                    modifier = Modifier.fillMaxSize()
//                )
//            }
//
//            Spacer(modifier = Modifier.width(16.dp))
//
//            Column(modifier = Modifier.weight(1f)) {
//                Text(
//                    text = exercise.name,
//                    color = Color(0xFFCFCFCF),
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = exercise.description,
//                    color = Color(0xFFCFCFCF),
//                    fontSize = 12.sp
//                )
//            }
//
//            IconButton(
//                onClick = { onFavoriteClick(exercise) } // Favoritar/Desfavoritar
//            ) {
//                Icon(
//                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
//                    contentDescription = if (isFavorite) "Remover dos favoritos" else "Adicionar aos favoritos",
//                    tint = if (isFavorite) Color.Red else Color.Gray
//                )
//            }
//        }
//    }
//}

//

//@Composable
//fun ExerciseCard(
//    exercise: Exercise,
//    isFavorite: Boolean,
//    onFavoriteClick: (Exercise) -> Unit, // Aceita um parâmetro Exercise
//    onClick: () -> Unit
//) {
//    Card(
//        shape = RoundedCornerShape(16.dp),
//        backgroundColor = Color(0xFF626262),
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable(onClick = onClick)
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Image(
//                painter = painterResource(id = exercise.image),
//                contentDescription = exercise.name,
//                modifier = Modifier
//                    .size(64.dp)
//                    .clip(RoundedCornerShape(8.dp))
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
//                Text(
//                    text = exercise.name,
//                    color = Color(0xFFCFCFCF),
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = exercise.description,
//                    color = Color(0xFFCFCFCF),
//                    fontSize = 14.sp
//                )
//            }
//            IconButton(onClick = { onFavoriteClick(exercise) }) { // Passa o exercício para o callback
//                Icon(
//                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
//                    contentDescription = if (isFavorite) "Desfavoritar" else "Favoritar",
//                    tint = if (isFavorite) Color.Red else Color(0xFFCFCFCF)
//                )
//            }
//        }
//    }
//}

//@Composable
//fun ExerciseCard(
//    exercise: Exercise,
//    isFavorite: Boolean,
//    onFavoriteClick: (Exercise) -> Unit,
//    onClick: () -> Unit
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(100.dp)
//            .clickable { onClick() },
//        shape = RoundedCornerShape(16.dp),
//        backgroundColor = Color(0xFF626262)
//    ) {
//        Row(
//            modifier = Modifier.fillMaxSize(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                painter = painterResource(id = exercise.image),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(80.dp)
//                    .padding(8.dp)
//                    .clip(RoundedCornerShape(8.dp))
//            )
//            Column(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(8.dp)
//            ) {
//                Text(
//                    text = exercise.name,
//                    fontSize = 16.sp,
//                    color = Color(0xFFCFCFCF),
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = exercise.description,
//                    fontSize = 14.sp,
//                    color = Color(0xFFCFCFCF)
//                )
//            }
//            IconButton(onClick = { onFavoriteClick(exercise) }) {
//                Icon(
//                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
//                    contentDescription = null,
//                    tint = if (isFavorite) Color.Red else Color(0xFFCFCFCF)
//                )
//            }
//        }
//    }
//}






//@Preview(showBackground = true)
//@Composable
//fun PreviewExerciseListScreen() {
//    ExerciseListScreen(
//        muscleGroup = "Braço",
//        exercises = mockExercises,
//        onExerciseClick = {},
//        onBackClick = {}
//    )
//}

