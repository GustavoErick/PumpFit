package com.example.pumpfit.model.data


import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.userProfileChangeRequest

import kotlinx.coroutines.tasks.await

class AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun registerUser(email: String, password: String, name: String, onResult: (Boolean, String?) -> Unit) {
        try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user

            if (user != null) {
                user.updateProfile(userProfileChangeRequest {
                    displayName = name
                }).await()

                onResult(true, "Cadastro realizado com sucesso!")
            } else {
                onResult(false, "Erro ao criar conta.")
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Erro no cadastro: ${e.message}")
            onResult(false, e.message)
        }
    }


    suspend fun loginUser(
        email: String,
        password: String
    ): Boolean {
        return try {
            auth.signInWithEmailAndPassword(
                email,
                password
            ).await()
            true
        } catch (e: Exception) {
            Log.e("AuthRepository", "Erro login ${e.message}")
            false
        }
    }

    suspend fun resetPassword(
        email: String
    ): Boolean {
        return try {
            auth.sendPasswordResetEmail(email).await()
            true
        } catch (e: Exception) {
            Log.e("AuthRepository", "Erro ao enviar email de recuperação de senha: ${e.message}")
            false
        }
    }


    fun getGoogleSignInClient(context: android.content.Context): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(com.example.pumpfit.R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso)
    }


    suspend fun loginWithGoogle(idToken: String): Boolean {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(credential).await()
            val user = result.user

            user?.let {
                Log.d("AuthRepository", "Usuário logado com Google: ${it.displayName}, ${it.email}")
            }

            user != null
        } catch (e: Exception) {
            Log.e("AuthRepository", "Erro no login com Google: ${e.message}")
            false
        }
    }

    fun getUserName(): String? {
        return auth.currentUser?.displayName
    }

    fun getUserEmail(): String? {
        return auth.currentUser?.email
    }

    fun logout() {
        auth.signOut()
    }

    fun isUserLogged(): Boolean {
        return auth.currentUser != null
    }
}