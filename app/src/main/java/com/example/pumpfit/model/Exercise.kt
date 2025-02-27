package com.example.pumpfit.model

data class Exercise(
    val id: String,
    val name: String,
    val description: String,
    val muscleGroup: String,
    val weight: String,
    val sets: String,
    val methodology: String,
    val interval: String,
    val image: Int,
    val video: Int,
    val machines: List<String>
)
