package com.example.pumpfit.model.mock

import com.example.pumpfit.R
import com.example.pumpfit.model.Exercise

val mockExercises = listOf(
    Exercise(
        id = "1",
        name = "Rosca Direta",
        description = "Exercício para bíceps utilizando barra.",
        muscleGroup = "Braço",
        weight = "30kg",
        sets = "3x15-12-10",
        methodology = "Tensional/Corda",
        interval = "01:55",
        image = R.drawable.placeholder_image,
        video = R.raw.placeholder_video,
        machines = listOf("Barra Reta", "Halteres", "Máquina de Rosca")
    ),
    Exercise(
        id = "2",
        name = "Supino Reto",
        description = "Exercício para peitoral realizado no banco reto.",
        muscleGroup = "Peito",
        weight = "40kg",
        sets = "4x12-10-8-6",
        methodology = "Progressivo",
        interval = "02:00",
        image = R.drawable.placeholder_image,
        video = R.raw.placeholder_video,
        machines = listOf("Banco Reto", "Halteres", "Barra Olímpica")
    )
)
