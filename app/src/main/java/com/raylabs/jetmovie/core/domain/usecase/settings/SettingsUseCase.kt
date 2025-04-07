package com.raylabs.jetmovie.core.domain.usecase.settings

import kotlinx.coroutines.flow.Flow

interface SettingsUseCase {
    fun isDarkTheme(): Flow<Boolean>
    suspend fun setDarkTheme(isActive: Boolean)
    fun isReminderActive(): Flow<Boolean>
    suspend fun setReminderStatus(isActive: Boolean)
}