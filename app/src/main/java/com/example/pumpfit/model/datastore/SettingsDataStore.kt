package com.example.pumpfit.model.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class SettingsDataStore(private val context: Context) {

    companion object {
        val NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
        val IS_DARK_THEME = booleanPreferencesKey("is_dark_theme")
        val USE_DEVICE_THEME = booleanPreferencesKey("use_device_theme")
        val VISUAL_ANIMATIONS = booleanPreferencesKey("visual_animations")
        val FAVORITES = stringSetPreferencesKey("favorites")
    }

    val notificationsEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[NOTIFICATIONS_ENABLED] ?: true }

    val isDarkTheme: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[IS_DARK_THEME] ?: false }

    val visualAnimations: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[VISUAL_ANIMATIONS] ?: true }

    val useDeviceTheme: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[USE_DEVICE_THEME] ?: true }

    val favorites: Flow<Set<String>> = context.dataStore.data
        .map { preferences -> preferences[FAVORITES] ?: emptySet() }

    suspend fun setNotificationsEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[NOTIFICATIONS_ENABLED] = enabled
        }
    }

    suspend fun setDarkTheme(isDark: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_DARK_THEME] = isDark
        }
    }

    suspend fun setVisualAnimations(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[VISUAL_ANIMATIONS] = enabled
        }
    }

    suspend fun setUseDeviceTheme(useDeviceTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[USE_DEVICE_THEME] = useDeviceTheme
        }
    }

    suspend fun addFavorite(item: String) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[FAVORITES] ?: emptySet()
            preferences[FAVORITES] = currentFavorites + item
        }
    }

    suspend fun removeFavorite(item: String) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[FAVORITES] ?: emptySet()
            preferences[FAVORITES] = currentFavorites - item
        }
    }

    suspend fun clearFavorites() {
        context.dataStore.edit { preferences ->
            preferences[FAVORITES] = emptySet()
        }
    }

    val IS_USER_LOGGED_IN = booleanPreferencesKey("is_user_logged_in")

    val isUserLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[IS_USER_LOGGED_IN] ?: false }

    suspend fun setUserLoggedIn(loggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_USER_LOGGED_IN] = loggedIn
        }
    }

}
