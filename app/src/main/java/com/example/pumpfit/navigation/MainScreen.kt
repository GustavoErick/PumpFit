package com.example.pumpfit.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
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
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.example.pumpfit.model.viewmodels.ExerciseViewModel
import com.example.pumpfit.ui.screen.ConfigScreen
import com.example.pumpfit.ui.screen.FavoritesScreen
import com.example.pumpfit.ui.screen.HelpScreen
import com.example.pumpfit.ui.screen.ProfileScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun MainScreen(isDarkTheme: Boolean, settingsDataStore: SettingsDataStore) {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    val favoriteExercises = remember { mutableStateListOf<Exercise>() }
    var favoritesFromDataStorage by remember { mutableStateOf(emptyList<String>()) }
    LaunchedEffect(Unit) {
        favoritesFromDataStorage = settingsDataStore.favorites.first().toList()
        favoritesFromDataStorage.forEach { id ->
            mockExercises.find { it.id == id }?.let { favoriteExercises.add(it) }
        }
    }

    val coroutineScope = rememberCoroutineScope()
    val animationsEnabled = runBlocking { settingsDataStore.visualAnimations.first() }
    val viewModelExercise: ExerciseViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)

    fun toggleFavorite(
        exercise: Exercise,
        favoriteExercises: SnapshotStateList<Exercise>,
        settingsDataStore: SettingsDataStore,
        coroutineScope: CoroutineScope
    ) {
        if (favoriteExercises.contains(exercise)) {
            coroutineScope.launch { settingsDataStore.removeFavorite(exercise.id) }
        } else {
            coroutineScope.launch { settingsDataStore.addFavorite(exercise.id) }
        }
    }



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

                if(animationsEnabled){
                    var isVisible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        isVisible = true // Torna a tela visível
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }), // Animação de entrada
                    ){
                        HomeScreen(
                            userId = "5", // ID de usuário no mock
                            navController = navController,
                            onMuscleGroupSelected = { muscleGroupId ->
                                navController.navigate("exerciseList/$muscleGroupId")
                            }
                        )
                    }
                } else {
                    HomeScreen(
                        userId = "5", // ID de usuário no mock
                        navController = navController,
                        onMuscleGroupSelected = { muscleGroupId ->
                            navController.navigate("exerciseList/$muscleGroupId")
                        }
                    )
                }
            }

            composable("exerciseList/{muscleGroupId}") { backStackEntry ->
                val muscleGroupId = backStackEntry.arguments?.getString("muscleGroupId") ?: ""
                val muscleGroup = mockMuscleGroups.find { it.id == muscleGroupId }

                // Lista de exercícios filtrados pelo grupo muscular
                val exercises = mockExercises.filter { it.muscleGroup == muscleGroup?.name }

                if(animationsEnabled){
                    var isVisible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        isVisible = true // Torna a tela visível
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }), // Animação de entrada
                    ){
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
                                    // logica usando o settingsDataStore
                                    coroutineScope.launch {
                                        settingsDataStore.removeFavorite(exercise.id)
                                    }
                                } else {
                                    favoriteExercises.add(exercise) // Adiciona aos favoritos
                                    // logica usando o settingsDataStore
                                    coroutineScope.launch {
                                        settingsDataStore.addFavorite(exercise.id)
                                    }
                                }

                            },
                            onBackClick = { navController.popBackStack() } // Volta para a tela anterior
                        )
                    }
                } else {
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
                                // logica usando o settingsDataStore
                                coroutineScope.launch {
                                    settingsDataStore.removeFavorite(exercise.id)
                                }
                            } else {
                                favoriteExercises.add(exercise) // Adiciona aos favoritos
                                // logica usando o settingsDataStore
                                coroutineScope.launch {
                                    settingsDataStore.addFavorite(exercise.id)
                                }
                            }
                        },
                        onBackClick = { navController.popBackStack() } // Volta para a tela anterior
                    )
                }


            }


            composable("exerciseDetails/{exerciseId}") { backStackEntry ->
                val exerciseId = backStackEntry.arguments?.getString("exerciseId") ?: ""
                val exercise = mockExercises.find { it.id == exerciseId }

                if(animationsEnabled){
                    var isVisible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        isVisible = true // Torna a tela visível
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }), // Animação de entrada
                    ){
                        exercise?.let {
                            ExerciseDetailsScreen(
                                exercise = it,
                                onBackClick = { navController.popBackStack() },
                                viewModel = viewModelExercise,
                            )
                        }
                    }
                } else {
                    exercise?.let {
                        ExerciseDetailsScreen(
                            exercise = it,
                            onBackClick = { navController.popBackStack() },
                            viewModel = viewModelExercise,
                        )
                    }
                }


            }

            composable("favorites") {

                if(animationsEnabled){
                    var isVisible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        isVisible = true // Torna a tela visível
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }), // Animação de entrada
                    ){
                        FavoritesScreen(
                            favoriteExercises = favoriteExercises,
                            onExerciseClick = { exerciseId ->
                                navController.navigate("exerciseDetails/$exerciseId")
                            },
                            onFavoriteClick = { exercise ->
                                favoriteExercises.remove(exercise)
                                coroutineScope.launch {
                                    settingsDataStore.removeFavorite(exercise.id)
                                }
                            },
                            onBackClick = { navController.popBackStack() },
                            viewModel = viewModelExercise
                        )
                    }
                } else {
                    FavoritesScreen(
                        favoriteExercises = favoriteExercises,
                        onExerciseClick = { exerciseId ->
                            navController.navigate("exerciseDetails/$exerciseId")
                        },
                        onFavoriteClick = { exercise ->
                            favoriteExercises.remove(exercise)
                            coroutineScope.launch {
                                settingsDataStore.removeFavorite(exercise.id)
                            }
                        },
                        onBackClick = { navController.popBackStack() },
                        viewModel = viewModelExercise
                    )
                }
            }

            composable("profile") {

                if(animationsEnabled){
                    var isVisible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        isVisible = true // Torna a tela visível
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }), // Animação de entrada
                    ){
                        ProfileScreen(
                            userId = "5", // ID de usuário no mock
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                } else {
                    ProfileScreen(
                        userId = "5", // ID de usuário no mock
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
            }

            composable("settings") {
                val context = LocalContext.current
                val settingsDataStore = SettingsDataStore(context)
                val scope = rememberCoroutineScope()

                if(animationsEnabled){
                    var isVisible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        isVisible = true // Torna a tela visível
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }), // Animação de entrada
                    ){
                        ConfigScreen(
                            onBackClick = { navController.popBackStack() },
                            onClearFavorites = {
                                favoriteExercises.clear()
                                coroutineScope.launch {
                                    settingsDataStore.clearFavorites()
                                    snackBarHostState.showSnackbar("Favoritos limpados com sucesso!")
                                }
                            },
                            settingsDataStore = settingsDataStore,
                            scope = scope,
                        )
                    }
                } else {
                    ConfigScreen(
                        onBackClick = { navController.popBackStack() },
                        onClearFavorites = {
                            favoriteExercises.clear()
                            coroutineScope.launch {
                                settingsDataStore.clearFavorites()
                                snackBarHostState.showSnackbar("Favoritos limpados com sucesso!")
                            }
                        },
                        settingsDataStore = settingsDataStore,
                        scope = scope,
                    )
                }
            }

            composable("help") {

                if(animationsEnabled){
                    var isVisible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        isVisible = true // Torna a tela visível
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }), // Animação de entrada
                    ){
                        HelpScreen(onBackClick = { navController.popBackStack() })
                    }
                } else {
                    HelpScreen(onBackClick = { navController.popBackStack() })
                }
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
                    popUpTo("home") { inclusive = true }
                    launchSingleTop = true
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