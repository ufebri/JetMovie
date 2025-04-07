package com.raylabs.jetmovie.core.di

import android.content.Context
import com.raylabs.jetmovie.core.data.datastore.SettingPreferences
import com.raylabs.jetmovie.core.data.repository.SettingRepository
import com.raylabs.jetmovie.core.domain.repository.ISettingsRepository
import com.raylabs.jetmovie.core.domain.usecase.settings.SettingsInteractor
import com.raylabs.jetmovie.core.domain.usecase.settings.SettingsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideSettingPreferences(@ApplicationContext context: Context): SettingPreferences =
        SettingPreferences(context)

    @Provides
    @Singleton
    fun provideSettingRepository(pref: SettingPreferences): ISettingsRepository =
        SettingRepository(pref)

    @Provides
    @Singleton
    fun provideSettingUseCase(repository: SettingRepository): SettingsUseCase =
        SettingsInteractor(repository)
}