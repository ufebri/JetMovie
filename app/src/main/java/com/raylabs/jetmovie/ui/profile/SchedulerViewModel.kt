package com.raylabs.jetmovie.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raylabs.jetmovie.data.repository.scheduler.SchedulerRepository
import kotlinx.coroutines.launch

class SchedulerViewModel(private val repository: SchedulerRepository) : ViewModel() {

    fun setScheduler() = viewModelScope.launch { repository.scheduleSuggestion() }
}