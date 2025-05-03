package com.raylabs.jetmovie.data.repository.settings

import com.raylabs.jetmovie.data.repository.SettingsRepository
import com.raylabs.jetmovie.data.source.preferences.SettingPreferences
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class SettingsRepositoryTest {

    private lateinit var repository: SettingsRepository
    private lateinit var mockPreferences: SettingPreferences

    @Before
    fun setUp() {
        mockPreferences = Mockito.mock(SettingPreferences::class.java)
        repository = SettingsRepository.Companion.getInstance(mockPreferences)
    }

    @Test
    fun `test isDarkTheme returns correct value`() = runBlocking {
        // Simulasi DataStore mengembalikan true untuk tema gelap
        Mockito.`when`(mockPreferences.getFlagSetting("theme_setting")).thenReturn(flowOf(true))

        val result = repository.isDarkTheme()

        // Verifikasi bahwa nilai yang dikembalikan adalah true
        result.collect {
            Assert.assertEquals(true, it)
        }
    }

    @Test
    fun `test setDarkTheme saves correct value`() = runBlocking {
        repository.setDarkTheme(true)

        // Verifikasi bahwa metode saveFlagSetting dipanggil dengan benar
        Mockito.verify(mockPreferences).saveFlagSetting(true, "theme_setting")
    }

    @Test
    fun `test isReminderActive returns correct value`() = runBlocking {
        // Simulasi DataStore mengembalikan false untuk reminder
        Mockito.`when`(mockPreferences.getFlagSetting("reminder_setting")).thenReturn(flowOf(false))

        val result = repository.isReminderActive()

        // Verifikasi bahwa nilai yang dikembalikan adalah false
        result.collect {
            Assert.assertEquals(false, it)
        }
    }

    @Test
    fun `test setReminderStatus saves correct value`() = runBlocking {
        repository.setReminderStatus(true)

        // Verifikasi bahwa metode saveFlagSetting dipanggil dengan benar
        Mockito.verify(mockPreferences).saveFlagSetting(true, "reminder_setting")
    }

    @Test
    fun `test isDiscoverActive returns correct value`() = runBlocking {
        // Simulasi DataStore mengembalikan false untuk dicover
        Mockito.`when`(mockPreferences.getFlagSetting("discover_setting")).thenReturn(flowOf(false))

        val result = repository.isDiscoverActive()

        // Verifikasi bahwa nilai yang dikembalikan adalah false
        result.collect {
            Assert.assertEquals(false, it)
        }
    }

    @Test
    fun `test setDiscoverActive saves correct value`() = runBlocking {
        repository.setDiscoverActive(true)

        // Verifikasi bahwa metode saveFlagSetting dipanggil dengan benar
        Mockito.verify(mockPreferences).saveFlagSetting(true, "discover_setting")
    }
}