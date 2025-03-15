package com.raylabs.jetmovie.data.repository

import com.raylabs.jetmovie.data.source.preferences.SettingPreferences
import kotlinx.coroutines.flow.Flow

class SettingsRepository private constructor(
    private val preferences: SettingPreferences
) : SettingsSource {

    companion object {
        @Volatile
        private var instance: SettingsRepository? = null

        private const val REMINDER_KEY = "reminder_setting"
        private const val THEME_KEY = "theme_setting"

        fun getInstance(
            settingPreferences: SettingPreferences
        ): SettingsRepository =
            instance ?: synchronized(this) {
                instance ?: SettingsRepository(settingPreferences)
            }
    }

    override fun isDarkTheme(): Flow<Boolean> = preferences.getFlagSetting(THEME_KEY)

    override suspend fun setDarkTheme(isActive: Boolean) =
        preferences.saveFlagSetting(isActive, THEME_KEY)

    override fun isReminderActive(): Flow<Boolean> = preferences.getFlagSetting(REMINDER_KEY)

    override suspend fun setReminderStatus(isActive: Boolean) =
        preferences.saveFlagSetting(isActive, REMINDER_KEY)


}