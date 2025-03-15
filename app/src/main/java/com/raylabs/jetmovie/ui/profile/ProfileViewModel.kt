package com.raylabs.jetmovie.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.raylabs.jetmovie.data.repository.SettingsRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: SettingsRepository) : ViewModel() {

    val isReminderActive = repository.isReminderActive().asLiveData()

    fun setReminder(isActive: Boolean) = viewModelScope.launch { repository.setReminderStatus(isActive) }
}