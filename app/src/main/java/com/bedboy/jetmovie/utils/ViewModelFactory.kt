package com.bedboy.jetmovie.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bedboy.jetmovie.data.repository.SettingsRepository
import com.bedboy.jetmovie.data.source.DataRepository
import com.bedboy.jetmovie.ui.detail.DetailViewModel
import com.bedboy.jetmovie.ui.home.HomeViewModel
import com.bedboy.jetmovie.ui.profile.ProfileViewModel
import com.bedboy.jetmovie.ui.upcoming.UpcomingViewModel
import com.bedboy.jetmovie.ui.watchlist.WatchListViewModel

class ViewModelFactory private constructor(
    private val dataRepository: DataRepository,
    private val settingsRepository: SettingsRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideRepository(context),
                    Injection.provideSettingRepository(context)
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

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}