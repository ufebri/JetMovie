package com.bedboy.jetmovie.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bedboy.jetmovie.data.source.DataRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val dataRepository: DataRepository) : ViewModel() {

    val themeSetting: LiveData<Boolean> = dataRepository.getThemeSetting().asLiveData()

    fun saveThemeSetting(isDarkMode: Boolean) {
        viewModelScope.launch { dataRepository.saveThemeSetting(isDarkMode) }
    }
}