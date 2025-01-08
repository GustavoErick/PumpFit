package com.example.pumpfit.model

data class Exercise(
    val id: String,
    val name: String,
    val description: String,
    val muscleGroup: String,
    val weight: String, // Exemplo: "30kg"
    val sets: String, // Alterado de "series" para "sets" para consistência no inglês
    val methodology: String, // Exemplo: "Tensional/Corda"
    val interval: String, // Exemplo: "01:55"
    val image: Int, // Recurso de imagem do exercício
    val video: Int, // Recurso de vídeo do exercício
    val machines: List<String> // Lista de máquinas associadas ao exercício
)
