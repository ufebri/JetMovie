package com.raylabs.jetmovie.data.source.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    fun getFlagSetting(key: String): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            val dataKey = booleanPreferencesKey(key)
            preferences[dataKey] == true
        }
    }

    suspend fun saveFlagSetting(isActive: Boolean, key: String) {
        dataStore.edit { preferences ->
            val dataKey = booleanPreferencesKey(key)
            preferences[dataKey] = isActive
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: SettingPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): SettingPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}