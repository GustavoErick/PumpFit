package com.example.pumpfit.network

import retrofit2.http.GET

interface MuscleApiService {
    @GET("muscle-groups")
    suspend fun getMuscleGroups(): List<String>
}

