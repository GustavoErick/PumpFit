package com.example.pumpfit.network

import com.example.pumpfit.model.MuscleGroupApiResponse
import retrofit2.http.GET

interface MuscleApiService {
    @GET("muscle-groups")
    suspend fun getMuscleGroups(): List<MuscleGroupApiResponse>
}

