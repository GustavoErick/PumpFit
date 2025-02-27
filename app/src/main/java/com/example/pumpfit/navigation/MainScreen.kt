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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.example.pumpfit.model.viewmodels.AuthViewModel
import com.example.pumpfit.model.viewmodels.ExerciseViewModel
import com.example.pumpfit.ui.screen.ConfigScreen
import com.example.pumpfit.ui.screen.FavoritesScreen
import com.example.pumpfit.ui.screen.HelpScreen
import com.example.pumpfit.ui.screen.ProfileScreen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import com.example.pumpfit.ui.screen.*

@Composable
fun MainScreen(isDarkTheme: Boolean, settingsDataStore: SettingsDataStore, authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    val favoriteExercises = remember { mutableStateListOf<Exercise>() }
    var favoritesFromDataStorage by remember { mutableStateOf(emptyList<String>()) }
    val viewModelExercise: ExerciseViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)

    LaunchedEffect(Unit) {
        favoritesFromDataStorage = settingsDataStore.favorites.first().toList()
        favoritesFromDataStorage.forEach { id ->
            mockExercises.find { it.id == id }?.let { favoriteExercises.add(it) }
        }
    }

    val coroutineScope = rememberCoroutineScope()
    val animationsEnabled = runBlocking { settingsDataStore.visualAnimations.first() }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomBar = currentRoute !in listOf("login", "register", "forgotPassword")

    Scaffold(
        bottomBar = { if (showBottomBar) BottomNavigationBar(navController) },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if (authViewModel.isUserLogged.collectAsState(initial = false).value) "home" else "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") {
                if(animationsEnabled){
                    var isVisible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        isVisible = true
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }),
                    ){
                        LoginScreen(
                            viewModel = authViewModel,
                            navController = navController,
                            settingsDataStore = settingsDataStore
                        )
                    }
                } else {
                    LoginScreen(
                        viewModel = authViewModel,
                        navController = navController,
                        settingsDataStore = settingsDataStore
                    )
                }
            }

            composable("register") {
                if(animationsEnabled){
                    var isVisible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        isVisible = true
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }),
                    ){
                        RegisterScreen(
                            viewModel = authViewModel,
                            navController = navController,
                            settingsDataStore = settingsDataStore
                        )
                    }
                } else {
                    RegisterScreen(
                        viewModel = authViewModel,
                        navController = navController,
                        settingsDataStore = settingsDataStore
                    )
                }
            }

            composable("forgotPassword") {
                if(animationsEnabled){
                    var isVisible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        isVisible = true
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }),
                    ){
                        ForgotPasswordScreen(
                            viewModel = authViewModel,
                            navController = navController
                        )
                    }
                } else {
                    ForgotPasswordScreen(
                        viewModel = authViewModel,
                        navController = navController
                    )
                }
            }

            composable("home") {

                if(animationsEnabled){
                    var isVisible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        isVisible = true
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }),
                    ){
                        HomeScreen(
                            userId = "5",
                            navController = navController,
                            onMuscleGroupSelected = { muscleGroupId ->
                                navController.navigate("exerciseList/$muscleGroupId")
                            },
                            authViewModel
                        )
                    }
                } else {
                    HomeScreen(
                        userId = "5",
                        navController = navController,
                        onMuscleGroupSelected = { muscleGroupId ->
                            navController.navigate("exerciseList/$muscleGroupId")
                        },
                        authViewModel
                    )
                }
            }

            composable("exerciseList/{muscleGroupId}") { backStackEntry ->
                val muscleGroupId = backStackEntry.arguments?.getString("muscleGroupId") ?: ""
                val muscleGroup = mockMuscleGroups.find { it.id == muscleGroupId }

                val exercises = mockExercises.filter { it.muscleGroup == muscleGroup?.name }

                if(animationsEnabled){
                    var isVisible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        isVisible = true
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }),
                    ){
                        ExerciseListScreen(
                            muscleGroup = muscleGroup?.name ?: "Desconhecido",
                            exercises = exercises,
                            favoriteExercises = favoriteExercises,
                            onExerciseClick = { exerciseId ->
                                navController.navigate("exerciseDetails/$exerciseId")
                            },
                            onFavoriteClick = { exercise ->
                                if (favoriteExercises.contains(exercise)) {
                                    favoriteExercises.remove(exercise)
                                    coroutineScope.launch {
                                        settingsDataStore.removeFavorite(exercise.id)
                                    }
                                } else {
                                    favoriteExercises.add(exercise)
                                    coroutineScope.launch {
                                        settingsDataStore.addFavorite(exercise.id)
                                    }
                                }

                            },
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                } else {
                    ExerciseListScreen(
                        muscleGroup = muscleGroup?.name ?: "Desconhecido",
                        exercises = exercises,
                        favoriteExercises = favoriteExercises,
                        onExerciseClick = { exerciseId ->
                            navController.navigate("exerciseDetails/$exerciseId")
                        },
                        onFavoriteClick = { exercise ->
                            if (favoriteExercises.contains(exercise)) {
                                favoriteExercises.remove(exercise)
                                coroutineScope.launch {
                                    settingsDataStore.removeFavorite(exercise.id)
                                }
                            } else {
                                favoriteExercises.add(exercise)
                                coroutineScope.launch {
                                    settingsDataStore.addFavorite(exercise.id)
                                }
                            }
                        },
                        onBackClick = { navController.popBackStack() }
                    )
                }


            }


            composable("exerciseDetails/{exerciseId}") { backStackEntry ->
                val exerciseId = backStackEntry.arguments?.getString("exerciseId") ?: ""
                val exercise = mockExercises.find { it.id == exerciseId }

                if(animationsEnabled){
                    var isVisible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        isVisible = true
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }),
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
                        isVisible = true
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }),
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
                        isVisible = true
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }),
                    ){
                        ProfileScreen(
                            userId = "5",
                            onBackClick = {
                                navController.popBackStack()
                            },
                            authViewModel
                        )
                    }
                } else {
                    ProfileScreen(
                        userId = "5",
                        onBackClick = {
                            navController.popBackStack()
                        },
                        authViewModel
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
                        isVisible = true
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }),
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
                        isVisible = true
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }),
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