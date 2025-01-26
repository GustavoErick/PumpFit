package com.example.pumpfit.model.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExerciseViewModel : ViewModel() {
    private var _timeRemaining = mutableStateOf(0L)
    val timeRemaining: State<Long> = _timeRemaining

    private var timerJob: Job? = null

    fun startTimer(initialTime: Long) {
        _timeRemaining.value = initialTime

        // Cancel any existing job before starting a new one
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_timeRemaining.value > 0) {
                delay(1000L)
                _timeRemaining.value -= 1000L
            }
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
    }
}
