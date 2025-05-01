package com.raylabs.jetmovie.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raylabs.jetmovie.data.repository.SettingsRepository
import com.raylabs.jetmovie.data.repository.scheduler.SchedulerRepository
import com.raylabs.jetmovie.data.source.DataRepository
import com.raylabs.jetmovie.ui.detail.DetailViewModel
import com.raylabs.jetmovie.ui.home.HomeViewModel
import com.raylabs.jetmovie.ui.profile.ProfileViewModel
import com.raylabs.jetmovie.ui.profile.SchedulerViewModel
import com.raylabs.jetmovie.ui.profile.ThemeViewModel
import com.raylabs.jetmovie.ui.upcoming.UpcomingViewModel
import com.raylabs.jetmovie.ui.watchlist.WatchListViewModel

class ViewModelFactory private constructor(
    private val dataRepository: DataRepository,
    private val settingsRepository: SettingsRepository,
    private val schedulerRepository: SchedulerRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideRepository(context),
                    Injection.provideSettingRepository(context),
                    Injection.provideSchedulerRepository(context)
                ).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(dataRepository) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(dataRepository) as T
            }

            modelClass.isAssignableFrom(WatchListViewModel::class.java) -> {
                WatchListViewModel(dataRepository) as T
            }

            modelClass.isAssignableFrom(UpcomingViewModel::class.java) -> {
                UpcomingViewModel(dataRepository) as T
            }

            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(settingsRepository) as T
            }

            modelClass.isAssignableFrom(ThemeViewModel::class.java) -> {
                ThemeViewModel(settingsRepository) as T
            }

            modelClass.isAssignableFrom(SchedulerViewModel::class.java) -> {
                SchedulerViewModel(schedulerRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}