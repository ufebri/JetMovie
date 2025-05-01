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
class ProfileViewModelTest {

    @get:Rule val instantExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: SettingsRepository
    private lateinit var viewModel: ProfileViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        repository = mock()
        whenever(repository.isReminderActive()).thenReturn(flowOf(true))
        whenever(repository.isDiscoverActive()).thenReturn(flowOf(false))

        viewModel = ProfileViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `isReminderActive should return true from repository`() = runTest {
        val observer = mock<Observer<Boolean>>()
        viewModel.isReminderActive.observeForever(observer)

        advanceUntilIdle()

        verify(observer).onChanged(true)
    }

    @Test
    fun `isDiscoverActive should return false from repository`() = runTest {
        val observer = mock<Observer<Boolean>>()
        viewModel.isDiscoverActive.observeForever(observer)

        advanceUntilIdle()

        verify(observer).onChanged(false)
    }

    @Test
    fun `setReminder should call repository with correct value`() = runTest {
        viewModel.setReminder(true)
        advanceUntilIdle()
        verify(repository).setReminderStatus(true)
    }

    @Test
    fun `setDiscover should call repository with correct value`() = runTest {
        viewModel.setDiscover(false)
        advanceUntilIdle()
        verify(repository).setDiscoverActive(false)
    }
}