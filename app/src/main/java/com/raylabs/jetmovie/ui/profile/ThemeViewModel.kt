package com.raylabs.jetmovie.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.raylabs.jetmovie.data.repository.SettingsRepository
import kotlinx.coroutines.launch

class ThemeViewModel(private val repository: SettingsRepository): ViewModel() {

    val isDarkThemeActive = repository.isDarkTheme().asLiveData()

    fun setDarkTheme(isActive: Boolean) = viewModelScope.launch { repository.setDarkTheme(isActive) }
}