package com.example.pumpfit.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pumpfit.model.Exercise
import com.example.pumpfit.ui.screen.ExerciseDetailsScreen
import com.example.pumpfit.ui.screen.ExerciseListScreen
import com.example.pumpfit.ui.screen.HomeScreen
import com.example.pumpfit.model.mock.mockExercises
import com.example.pumpfit.model.mock.mockMuscleGroups
//import com.example.pumpfit.ui.screen.FavoritesScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController) // Bottom Bar gerenciada aqui
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(
                    onMuscleGroupSelected = { muscleGroupId ->
                        navController.navigate("exerciseList/$muscleGroupId")
                    }
                )
            }
            composable("exerciseList/{muscleGroupId}") { backStackEntry ->
                val muscleGroupId = backStackEntry.arguments?.getString("muscleGroupId") ?: ""
                val muscleGroup = mockMuscleGroups.find { it.id == muscleGroupId }

                // Lista de exercícios filtrados pelo grupo muscular
                val exercises = mockExercises.filter { it.muscleGroup == muscleGroup?.name }

                ExerciseListScreen(
                    muscleGroup = muscleGroup?.name ?: "Desconhecido",
                    exercises = exercises,
                    onExerciseClick = { exerciseId ->
                        navController.navigate("exerciseDetails/$exerciseId") // Navega para detalhes
                    },
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable("exerciseDetails/{exerciseId}") { backStackEntry ->
                val exerciseId = backStackEntry.arguments?.getString("exerciseId") ?: ""
                val exercise = mockExercises.find { it.id == exerciseId }

                exercise?.let {
                    ExerciseDetailsScreen(
                        exercise = it,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
//@Composable
//fun MainScreen() {
//    val navController = rememberNavController()
//    val favoriteExercises = remember { mutableStateListOf<Exercise>() } // Lista de favoritos
//
//    Scaffold(
//        bottomBar = {
//            BottomNavigationBar(navController = navController)
//        }
//    ) { innerPadding ->
//        NavHost(
//            navController = navController,
//            startDestination = "home",
//            modifier = Modifier.padding(innerPadding)
//        ) {
//            // Tela Home
//            composable("home") {
//                HomeScreen(
//                    onMuscleGroupSelected = { muscleGroupId ->
//                        navController.navigate("exerciseList/$muscleGroupId")
//                    }
//                )
//            }
//
//            // Tela Lista de Exercícios
//            composable("exerciseList/{muscleGroupId}") { backStackEntry ->
//                val muscleGroupId = backStackEntry.arguments?.getString("muscleGroupId") ?: ""
//                val exercises = mockExercises.filter { it.muscleGroup.equals(muscleGroupId, ignoreCase = true) }
//
//                ExerciseListScreen(
//                    muscleGroup = muscleGroupId,
//                    exercises = exercises,
//                    favoriteExercises = favoriteExercises,
//                    onExerciseClick = { exerciseId ->
//                        navController.navigate("exerciseDetails/$exerciseId")
//                    },
//                    onFavoriteClick = { exercise ->
//                        if (favoriteExercises.contains(exercise)) {
//                            favoriteExercises.remove(exercise)
//                        } else {
//                            favoriteExercises.add(exercise)
//                        }
//                    }
//                )
//            }
//
//            // Tela Detalhes do Exercício
//            composable("exerciseDetails/{exerciseId}") { backStackEntry ->
//                val exerciseId = backStackEntry.arguments?.getString("exerciseId") ?: ""
//                val exercise = mockExercises.find { it.id == exerciseId }
//
//                exercise?.let {
//                    ExerciseDetailsScreen(
//                        exercise = it,
//                        onBackClick = { navController.popBackStack() }
//                    )
//                }
//            }
//
//            // Tela Favoritos
//            composable("favorites") {
//                FavoritesScreen(
//                    favoriteExercises = favoriteExercises,
//                    onExerciseClick = { exerciseId ->
//                        navController.navigate("exerciseDetails/$exerciseId")
//                    },
//                    onFavoriteClick = { exercise ->
//                        favoriteExercises.remove(exercise)
//                    }
//                )
//            }
//        }
//    }
//}



//            composable("exerciseList/{muscleGroupId}") { backStackEntry ->
//                val muscleGroupId = backStackEntry.arguments?.getString("muscleGroupId") ?: ""
//                val muscleGroup = mockMuscleGroups.find { it.id == muscleGroupId }
//
//                // Lista de exercícios filtrados pelo grupo muscular
//                val exercises = mockExercises.filter { it.muscleGroup == muscleGroup?.name }
//
//                ExerciseListScreen(
//                    muscleGroup = muscleGroup?.name ?: "Desconhecido",
//                    exercises = exercises,
//                    onExerciseClick = { exerciseId ->
//                        navController.navigate("exerciseDetails/$exerciseId")
//                    },
//                    onBackClick = { navController.popBackStack() }
//                )
//            }
//
//            composable("exerciseDetails/{exerciseId}") { backStackEntry ->
//                val exerciseId = backStackEntry.arguments?.getString("exerciseId") ?: ""
//                val exercise = mockExercises.find { it.id == exerciseId }
//                exercise?.let {
//                    ExerciseDetailsScreen(
//                        exercise = it,
//                        onBackClick = { navController.popBackStack() }
//                    )
//                }
//            }
//        }
//    }
//}

//@Composable
//fun MainScreen() {
//    val navController = rememberNavController()
//    val favoriteExercises = remember { mutableStateListOf<Exercise>() } // Lista de favoritos
//
//    Scaffold(
//        bottomBar = {
//            BottomNavigationBar(navController = navController)
//        }
//    ) { innerPadding ->
//        NavHost(
//            navController = navController,
//            startDestination = "home",
//            modifier = Modifier.padding(innerPadding)
//        ) {
//            // Tela Home
//            composable("home") {
//                HomeScreen(
//                    onMuscleGroupSelected = { muscleGroupId ->
//                        navController.navigate("exerciseList/$muscleGroupId")
//                    }
//                )
//            }
//
//            // Tela de Lista de Exercícios
//            composable("exerciseList/{muscleGroupId}") { backStackEntry ->
//                val muscleGroupId = backStackEntry.arguments?.getString("muscleGroupId") ?: ""
//                val exercises = mockExercises.filter { it.muscleGroup.equals(muscleGroupId, ignoreCase = true) }
//
//                ExerciseListScreen(
//                    muscleGroup = muscleGroupId,
//                    exercises = exercises,
//                    favoriteExercises = favoriteExercises,
//                    onExerciseClick = { exerciseId ->
//                        navController.navigate("exerciseDetails/$exerciseId")
//                    },
//                    onFavoriteClick = { exercise ->
//                        if (favoriteExercises.contains(exercise)) {
//                            favoriteExercises.remove(exercise)
//                        } else {
//                            favoriteExercises.add(exercise)
//                        }
//                    }
//                )
//            }
//
//            // Tela de Detalhes do Exercício
//            composable("exerciseDetails/{exerciseId}") { backStackEntry ->
//                val exerciseId = backStackEntry.arguments?.getString("exerciseId") ?: ""
//                val exercise = mockExercises.find { it.id == exerciseId }
//
//                exercise?.let {
//                    ExerciseDetailsScreen(
//                        exercise = it,
//                        onBackClick = { navController.popBackStack() }
//                    )
//                }
//            }
//
//            // Tela de Favoritos
//            composable("favorites") {
//                FavoritesScreen(
//                    favoriteExercises = favoriteExercises,
//                    onExerciseClick = { exerciseId ->
//                        navController.navigate("exerciseDetails/$exerciseId")
//                    },
//                    onFavoriteClick = { exercise ->
//                        favoriteExercises.remove(exercise)
//                    }
//                )
//            }
//        }
//    }
//}



@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = Color(0xFF090909),
        contentColor = Color(0xFFCFCFCF)
    ) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route

        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == "home",
            onClick = { navController.navigate("home") },
            selectedContentColor = Color.Red, // Cor do item selecionado
            unselectedContentColor = Color(0xFFCFCFCF) // Cor dos itens não selecionados
        )

        BottomNavigationItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
            label = { Text("Favoritos") },
            selected = currentRoute == "favorites",
            onClick = { navController.navigate("favorites") },
            selectedContentColor = Color.Red, // Cor do item selecionado
            unselectedContentColor = Color(0xFFCFCFCF) // Cor dos itens não selecionados
        )

        BottomNavigationItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Perfil") },
            selected = currentRoute == "profile",
            onClick = { navController.navigate("profile") },
            selectedContentColor = Color.Red,
            unselectedContentColor = Color(0xFFCFCFCF)
        )
    }
}

//@Composable
//fun BottomNavigationBar(navController: NavController) {
//    val currentRoute = navController.currentBackStackEntry?.destination?.route
//
//    BottomNavigation(
//        backgroundColor = Color(0xFF090909),
//        contentColor = Color(0xFFCFCFCF)
//    ) {
//        BottomNavigationItem(
//            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
//            label = { Text("Home") },
//            selected = currentRoute == "home",
//            onClick = { navController.navigate("home") }
//        )
//
//        BottomNavigationItem(
//            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
//            label = { Text("Favoritos") },
//            selected = currentRoute == "favorites",
//            onClick = { navController.navigate("favorites") }
//        )
//    }
//}



@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}



