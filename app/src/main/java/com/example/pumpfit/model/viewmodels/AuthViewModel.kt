package com.example.pumpfit.model.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pumpfit.model.data.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
): ViewModel() {

    val isUserLogged: Flow<Boolean> = flow {
        emit(repository.isUserLogged())
    }.stateIn(viewModelScope, SharingStarted.Lazily, repository.isUserLogged())

    fun login (
        email: String,
        password: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val success = repository.loginUser(email, password)
            onResult(success)
        }
    }

    fun resetPassword (
        email: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val success = repository.resetPassword(email)
            onResult(success)
        }
    }

    fun getUserName(onResult: (String?) -> Unit) {
        viewModelScope.launch {
            val name = repository.getUserName()
            onResult(name)
        }
    }

    fun loginWithGoogle(idToken: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = repository.loginWithGoogle(idToken)
            onResult(success)
        }
    }

    fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        return repository.getGoogleSignInClient(context)
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

    fun register(email: String, password: String, name: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            repository.registerUser(email, password, name) { success, message ->
                onResult(success, message)
            }
        }
    }

}
