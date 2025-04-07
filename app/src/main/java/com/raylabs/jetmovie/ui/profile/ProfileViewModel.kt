package com.raylabs.jetmovie.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.raylabs.jetmovie.core.domain.usecase.settings.SettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: SettingsUseCase) : ViewModel() {

    val isReminderActive = repository.isReminderActive().asLiveData()

    fun setReminder(isActive: Boolean) =
        viewModelScope.launch { repository.setReminderStatus(isActive) }
}