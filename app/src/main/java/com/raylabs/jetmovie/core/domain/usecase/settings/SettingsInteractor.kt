package com.raylabs.jetmovie.core.domain.usecase.settings

import com.raylabs.jetmovie.core.domain.repository.ISettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsInteractor @Inject constructor(private val repository: ISettingsRepository) :
    SettingsUseCase {

    override fun isDarkTheme(): Flow<Boolean> = repository.isDarkTheme()

    override suspend fun setDarkTheme(isActive: Boolean) = repository.setDarkTheme(isActive)

    override fun isReminderActive(): Flow<Boolean> = repository.isReminderActive()

    override suspend fun setReminderStatus(isActive: Boolean) =
        repository.setReminderStatus(isActive)
}