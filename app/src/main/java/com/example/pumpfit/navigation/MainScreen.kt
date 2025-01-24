package com.example.pumpfit.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pumpfit.model.Exercise
import com.example.pumpfit.model.datastore.SettingsDataStore
import com.example.pumpfit.ui.screen.ExerciseDetailsScreen
import com.example.pumpfit.ui.screen.ExerciseListScreen
import com.example.pumpfit.ui.screen.HomeScreen
import com.example.pumpfit.model.mock.mockExercises
import com.example.pumpfit.model.mock.mockMuscleGroups
import com.example.pumpfit.ui.screen.ConfigScreen
import com.example.pumpfit.ui.screen.FavoritesScreen
import com.example.pumpfit.ui.screen.HelpScreen
import com.example.pumpfit.ui.screen.ProfileScreen
import kotlinx.coroutines.launch

@Composable
//fun MainScreen(isDarkTheme: MutableState<Boolean>) {
//fun MainScreen(isDarkTheme: MutableState<Boolean>, settingsDataStore: SettingsDataStore) {
fun MainScreen(isDarkTheme: Boolean, settingsDataStore: SettingsDataStore) {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    val favoriteExercises = remember { mutableStateListOf<Exercise>() }

    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController) // Bottom Bar gerenciada aqui
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(
                    userId = "5", // ID de usuário no mock
                    navController = navController,
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
                    favoriteExercises = favoriteExercises, // Passa a lista de favoritos
                    onExerciseClick = { exerciseId ->
                        navController.navigate("exerciseDetails/$exerciseId") // Navega para a tela de detalhes do exercício
                    },
                    onFavoriteClick = { exercise ->
                        if (favoriteExercises.contains(exercise)) {
                            favoriteExercises.remove(exercise) // Remove dos favoritos
                        } else {
                            favoriteExercises.add(exercise) // Adiciona aos favoritos
                        }
                    },
                    onBackClick = { navController.popBackStack() } // Volta para a tela anterior
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

            composable("favorites") {
                FavoritesScreen(
                    favoriteExercises = favoriteExercises,
                    onExerciseClick = { exerciseId ->
                        navController.navigate("exerciseDetails/$exerciseId")
                    },
                    onFavoriteClick = { exercise ->
                        favoriteExercises.remove(exercise)
                    },
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable("profile") {
                ProfileScreen(
                    userId = "5", // ID de usuário no mock
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            composable("settings") {
                val context = LocalContext.current
                val settingsDataStore = SettingsDataStore(context)
                val scope = rememberCoroutineScope()

                ConfigScreen(
                    onBackClick = { navController.popBackStack() },
                    onClearFavorites = {
                        favoriteExercises.clear()
                        coroutineScope.launch {
                            snackBarHostState.showSnackbar("Favoritos limpados com sucesso!")
                        }
                    },
                    settingsDataStore = settingsDataStore,
                    scope = scope,
                )
            }

            composable("help") {
                HelpScreen(onBackClick = { navController.popBackStack() })
            }

        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.onBackground,
        contentColor = MaterialTheme.colorScheme.secondary
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = MaterialTheme.colorScheme.secondary
        )

        BottomNavigationItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
            label = { Text("Favoritos") },
            selected = currentRoute == "favorites",
            onClick = {
                navController.navigate("favorites") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = MaterialTheme.colorScheme.secondary
        )

        BottomNavigationItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Perfil") },
            selected = currentRoute == "profile",
            onClick = {
                navController.navigate("profile") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = MaterialTheme.colorScheme.secondary
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewMainScreen() {
//    MainScreen()
//}



