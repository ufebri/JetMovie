package com.raylabs.jetmovie.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface ISettingsRepository {

    fun isDarkTheme(): Flow<Boolean>

    suspend fun setDarkTheme(isActive: Boolean)

    fun isReminderActive(): Flow<Boolean>

    suspend fun setReminderStatus(isActive: Boolean)
}