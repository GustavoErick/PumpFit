package com.example.pumpfit.ui.screen

import com.example.pumpfit.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pumpfit.model.mock.mockUsers
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pumpfit.components.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pumpfit.model.MuscleGroupApiResponse
import com.example.pumpfit.model.viewmodels.AuthViewModel
import com.example.pumpfit.model.viewmodels.MuscleViewModel
import java.util.Calendar
import java.util.TimeZone

@Composable
fun HomeScreen(userId: String, navController: NavController, onMuscleGroupSelected: (String) -> Unit, authViewModel: AuthViewModel, viewModel: MuscleViewModel = viewModel()) {
    val muscleGroups by viewModel.muscleGroupsLiveData.observeAsState(emptyList())
    val isLoading by viewModel.isLoadingLiveData.observeAsState(initial = true)

    LaunchedEffect(Unit) {
        viewModel.fetchMuscleGroups()
    }

    val user = mockUsers.find { it.id == userId }
    val currentHour = remember {
        val tz = TimeZone.getDefault()
        val calendar = Calendar.getInstance(tz)
        calendar.get(Calendar.HOUR_OF_DAY)
    }

    val greetingMessage = when {
        currentHour in 5..11 -> "Bom dia,"
        currentHour in 12..17 -> "Boa tarde,"
        else -> "Boa noite,"
    }

    var searchQuery by remember { mutableStateOf("") }

    val filteredMuscleGroups = muscleGroups.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    var userName by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        authViewModel.getUserName { name ->
            userName = name ?: "Usuário não encontrado"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background( MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = greetingMessage,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = userName ?: "Carregando...",
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            Menu(navController, authViewModel)

        }

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("O que quer treinar hoje?", color = Color(0xFFCFCFCF)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(30.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF626262),
                unfocusedContainerColor = Color(0xFF626262),
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = Color(0xFFCFCFCF),
                cursorColor = Color(0xFFCFCFCF)
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Ícone de busca",
                    tint = Color(0xFFCFCFCF)
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
        }

        if (!isLoading){
            if (filteredMuscleGroups.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Nenhum resultado encontrado.",
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 16.sp
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredMuscleGroups) { group ->
                        MuscleGroupCard(
                            muscleGroup = group,
                            onClick = { onMuscleGroupSelected(group.id) }
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun MuscleGroupCard(muscleGroup: MuscleGroupApiResponse, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(Color.Gray)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = muscleGroup.imageRes,
            contentDescription = "Imagem de ${muscleGroup.name}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .fillMaxSize()
        )
        Text(
            text = muscleGroup.name,
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .background(Color.Black.copy(alpha = 0.5f), shape = RoundedCornerShape(30.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}