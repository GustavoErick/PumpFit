package com.example.pumpfit.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pumpfit.model.Exercise

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun FavoritesScreen(
//    favoriteExercises: List<Exercise>,
//    onExerciseClick: (String) -> Unit,
//    onFavoriteClick: (Exercise) -> Unit
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Favoritos", color = Color(0xFFCFCFCF)) },
//                backgroundColor = Color(0xFF090909),
//                contentColor = Color(0xFFCFCFCF)
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
//            if (favoriteExercises.isEmpty()) {
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = "Nenhum exercício favoritado",
//                        color = Color(0xFFCFCFCF),
//                        fontSize = 16.sp
//                    )
//                }
//            } else {
//                LazyColumn(
//                    verticalArrangement = Arrangement.spacedBy(16.dp)
//                ) {
//                    items(favoriteExercises) { exercise ->
//                        ExerciseCard(
//                            exercise = exercise,
//                            isFavorite = true,
//                            onFavoriteClick = onFavoriteClick,
//                            onClick = { onExerciseClick(exercise.id) }
//                        )
//                    }
//                }
//            }
//        }
//    }
//}

//@Composable
//fun FavoritesScreen(
//    favoriteExercises: List<Exercise>,
//    onExerciseClick: (String) -> Unit,
//    onFavoriteClick: (Exercise) -> Unit
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        text = "Favoritos",
//                        color = Color(0xFFCFCFCF),
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.Bold
//                    )
//                },
//                backgroundColor = Color(0xFF090909),
//                contentColor = Color(0xFFCFCFCF)
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
//            if (favoriteExercises.isEmpty()) {
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = "Nenhum exercício favorito",
//                        color = Color(0xFFCFCFCF),
//                        fontSize = 16.sp
//                    )
//                }
//            } else {
//                LazyColumn(
//                    verticalArrangement = Arrangement.spacedBy(16.dp)
//                ) {
//                    items(favoriteExercises) { exercise ->
//                        ExerciseCard(
//                            exercise = exercise,
//                            isFavorite = true,
//                            onFavoriteClick = onFavoriteClick,
//                            onClick = { onExerciseClick(exercise.id) }
//                        )
//                    }
//                }
//            }
//        }
//    }
//}

