package com.bedboy.jetmovie.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bedboy.jetmovie.data.repository.SettingsRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: SettingsRepository) : ViewModel() {

    val isDarkThemeActive = repository.isDarkTheme().asLiveData()

    fun setDarkTheme(isActive: Boolean) = viewModelScope.launch { repository.setDarkTheme(isActive) }

    val isReminderActive = repository.isReminderActive().asLiveData()

    fun setReminder(isActive: Boolean) = viewModelScope.launch { repository.setReminderStatus(isActive) }
}