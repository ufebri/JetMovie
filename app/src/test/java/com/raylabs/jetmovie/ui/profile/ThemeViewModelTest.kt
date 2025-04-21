package com.raylabs.jetmovie.ui.profile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.raylabs.jetmovie.data.repository.SettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class ThemeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: SettingsRepository
    private lateinit var viewModel: ThemeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        repository = mock()
        whenever(repository.isDarkTheme()).thenReturn(flowOf(true))

        viewModel = ThemeViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `isDarkThemeActive should return correct value from repository`() = runTest {
        val observer = mock<Observer<Boolean>>()
        viewModel.isDarkThemeActive.observeForever(observer)

        advanceUntilIdle() // ⬅️ jalanin semua coroutine yang tertunda

        verify(observer).onChanged(true)
    }

    @Test
    fun `setDarkTheme should call repository with correct value`() = runTest {
        viewModel.setDarkTheme(true)
        advanceUntilIdle()
        verify(repository).setDarkTheme(true)
    }

}