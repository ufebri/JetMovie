package com.raylabs.jetmovie.core.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingPreferences(private val context: Context) {

    fun getFlagSetting(key: String): Flow<Boolean> {
        val dataKey = booleanPreferencesKey(key)
        return context.dataStore.data.map { preferences ->
            preferences[dataKey] ?: false
        }
    }

    suspend fun saveFlagSetting(isActive: Boolean, key: String) {
        val dataKey = booleanPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[dataKey] = isActive
        }
    }
}