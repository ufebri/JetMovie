package com.raylabs.jetmovie.ui.profile

import com.nhaarman.mockitokotlin2.verify
import com.raylabs.jetmovie.data.repository.scheduler.SchedulerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class SchedulerViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: SchedulerRepository
    private lateinit var viewModel: SchedulerViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
        viewModel = SchedulerViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `setScheduler should call repository scheduleSuggestion`() = runTest {
        viewModel.setScheduler()
        advanceUntilIdle()
        verify(repository).scheduleSuggestion()
    }
}
