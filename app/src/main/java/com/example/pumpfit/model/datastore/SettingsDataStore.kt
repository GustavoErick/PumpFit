package com.example.pumpfit.model.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extensão para facilitar o acesso ao DataStore
val Context.dataStore by preferencesDataStore(name = "user_prefs")

class SettingsDataStore(private val context: Context) {

    // Definição das chaves para as preferências
    companion object {
        val NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
        val IS_DARK_THEME = booleanPreferencesKey("is_dark_theme")
        val VISUAL_ANIMATIONS = booleanPreferencesKey("visual_animations")
    }

    // Fluxo para acompanhar se as notificações estão ativadas
    val notificationsEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[NOTIFICATIONS_ENABLED] ?: true } // Padrão: ativado

    // Fluxo para acompanhar o estado do tema (escuro/claro)
    val isDarkTheme: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[IS_DARK_THEME] ?: false } // Padrão: claro

    // Fluxo para acompanhar se as animações visuais estão ativadas
    val visualAnimations: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[VISUAL_ANIMATIONS] ?: true } // Padrão: ativado

    // Função para atualizar o estado das notificações
    suspend fun setNotificationsEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[NOTIFICATIONS_ENABLED] = enabled
        }
    }

    // Função para atualizar o estado do tema (escuro/claro)
    suspend fun setDarkTheme(isDark: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_DARK_THEME] = isDark
        }
    }

    // Função para atualizar o estado das animações visuais
    suspend fun setVisualAnimations(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[VISUAL_ANIMATIONS] = enabled
        }
    }
}
