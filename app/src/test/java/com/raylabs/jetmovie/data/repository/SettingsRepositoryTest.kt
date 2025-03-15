package com.raylabs.jetmovie.data.repository

import com.raylabs.jetmovie.data.source.preferences.SettingPreferences
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class SettingsRepositoryTest {

    private lateinit var repository: SettingsRepository
    private lateinit var mockPreferences: SettingPreferences

    @Before
    fun setUp() {
        mockPreferences = mock(SettingPreferences::class.java)
        repository = SettingsRepository.getInstance(mockPreferences)
    }

    @Test
    fun `test isDarkTheme returns correct value`() = runBlocking {
        // Simulasi DataStore mengembalikan true untuk tema gelap
        `when`(mockPreferences.getFlagSetting("theme_setting")).thenReturn(flowOf(true))

        val result = repository.isDarkTheme()

        // Verifikasi bahwa nilai yang dikembalikan adalah true
        result.collect {
            assertEquals(true, it)
        }
    }

    @Test
    fun `test setDarkTheme saves correct value`() = runBlocking {
        repository.setDarkTheme(true)

        // Verifikasi bahwa metode saveFlagSetting dipanggil dengan benar
        verify(mockPreferences).saveFlagSetting(true, "theme_setting")
    }

    @Test
    fun `test isReminderActive returns correct value`() = runBlocking {
        // Simulasi DataStore mengembalikan false untuk reminder
        `when`(mockPreferences.getFlagSetting("reminder_setting")).thenReturn(flowOf(false))

        val result = repository.isReminderActive()

        // Verifikasi bahwa nilai yang dikembalikan adalah false
        result.collect {
            assertEquals(false, it)
        }
    }

    @Test
    fun `test setReminderStatus saves correct value`() = runBlocking {
        repository.setReminderStatus(true)

        // Verifikasi bahwa metode saveFlagSetting dipanggil dengan benar
        verify(mockPreferences).saveFlagSetting(true, "reminder_setting")
    }
}
