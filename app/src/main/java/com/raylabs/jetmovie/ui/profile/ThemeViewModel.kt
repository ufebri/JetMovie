package com.raylabs.jetmovie.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.raylabs.jetmovie.core.domain.usecase.settings.SettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(private val useCase: SettingsUseCase) : ViewModel() {

    val isDarkThemeActive = useCase.isDarkTheme().asLiveData()

    fun setDarkTheme(isActive: Boolean) = viewModelScope.launch { useCase.setDarkTheme(isActive) }
}