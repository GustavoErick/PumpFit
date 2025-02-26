package com.example.pumpfit.model.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pumpfit.network.RetrofitInstance
import kotlinx.coroutines.launch

class MuscleViewModel : ViewModel() {

    private val _muscleGroupsLiveData = MutableLiveData<List<String>>()
    val muscleGroupsLiveData: LiveData<List<String>> = _muscleGroupsLiveData

    fun fetchMuscleGroups() {
        viewModelScope.launch {
            try {
                val muscleGroups = RetrofitInstance.api.getMuscleGroups()
                Log.d("MuscleViewModel", "Dados recebidos: $muscleGroups")
                _muscleGroupsLiveData.postValue(muscleGroups)
            } catch (e: Exception) {
                Log.e("MuscleViewModel", "Erro na requisição: ${e.message}")
            }
        }
    }
}
