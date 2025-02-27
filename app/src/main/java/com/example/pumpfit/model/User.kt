package com.example.pumpfit.model


data class User(
    val id: String,
    val image: Int,
    val name: String,
    val email: String,
    val password: String,
    val weight: Double,
    val height: Double,
    val period: String
)