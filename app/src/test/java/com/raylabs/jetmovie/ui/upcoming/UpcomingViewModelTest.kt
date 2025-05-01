package com.raylabs.jetmovie.ui.upcoming

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.raylabs.jetmovie.data.source.DataRepository
import com.raylabs.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UpcomingViewModelTest {

    private lateinit var upcomingViewModel: UpcomingViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<DataMovieTVEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<DataMovieTVEntity>

    @Before
    fun setUp() {
        upcomingViewModel = UpcomingViewModel(dataRepository)
    }

    @Test
    fun getUpcoming() {
        val mPaging = Resource.success(pagedList)
        `when`(mPaging.data?.size).thenReturn(20)

        val upcoming = MutableLiveData<Resource<PagedList<DataMovieTVEntity>>>()
        upcoming.value = mPaging

        `when`(dataRepository.getAllUpcoming()).thenReturn(upcoming)
        val mData = upcomingViewModel.getAllUpcoming().value?.data
        verify(dataRepository).getAllUpcoming()

        assertNotNull(mData)
        assertEquals(20, mData?.size)

        upcomingViewModel.getAllUpcoming().observeForever(observer)
        verify(observer).onChanged(mPaging)
    }
}