package com.bedboy.jetmovie.data.repository

import kotlinx.coroutines.flow.Flow

interface SettingsSource {

    fun isDarkTheme(): Flow<Boolean>

    suspend fun setDarkTheme(isActive: Boolean)

    fun isReminderActive(): Flow<Boolean>

    suspend fun setReminderStatus(isActive: Boolean)
}