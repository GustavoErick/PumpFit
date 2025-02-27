package com.example.pumpfit.model.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pumpfit.model.MuscleGroupApiResponse
import com.example.pumpfit.network.RetrofitInstance
import kotlinx.coroutines.launch

class MuscleViewModel : ViewModel() {

    private val _muscleGroupsLiveData = MutableLiveData<List<MuscleGroupApiResponse>>()
    val muscleGroupsLiveData: LiveData<List<MuscleGroupApiResponse>> = _muscleGroupsLiveData

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData

    fun fetchMuscleGroups() {
        viewModelScope.launch {
            _isLoadingLiveData.postValue(true)
            try {
                val muscleGroups = RetrofitInstance.api.getMuscleGroups()
                Log.d("MuscleViewModel", "Dados recebidos: $muscleGroups")
                _muscleGroupsLiveData.postValue(muscleGroups)
            } catch (e: Exception) {
                Log.e("MuscleViewModel", "Erro na requisição: ${e.message}")
            } finally {
                _isLoadingLiveData.postValue(false)
            }
        }
    }
}
