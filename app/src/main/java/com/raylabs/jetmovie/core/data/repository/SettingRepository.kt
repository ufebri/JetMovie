package com.raylabs.jetmovie.core.data.repository

import com.raylabs.jetmovie.core.data.datastore.SettingPreferences
import com.raylabs.jetmovie.core.domain.repository.ISettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingRepository @Inject constructor(private val preferences: SettingPreferences) :
    ISettingsRepository {

    private companion object {
        private const val REMINDER_KEY = "reminder_setting"
        private const val THEME_KEY = "theme_setting"
    }

    override fun isDarkTheme(): Flow<Boolean> = preferences.getFlagSetting(THEME_KEY)

    override suspend fun setDarkTheme(isActive: Boolean) =
        preferences.saveFlagSetting(isActive, THEME_KEY)

    override fun isReminderActive(): Flow<Boolean> = preferences.getFlagSetting(REMINDER_KEY)

    override suspend fun setReminderStatus(isActive: Boolean) =
        preferences.saveFlagSetting(isActive, REMINDER_KEY)

}