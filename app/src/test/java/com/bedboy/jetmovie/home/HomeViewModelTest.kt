package com.bedboy.jetmovie.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bedboy.jetmovie.data.source.DataRepository
import com.bedboy.jetmovie.data.source.remote.response.ResultsItem
import com.bedboy.jetmovie.utils.DataDummy
import com.bedboy.jetmovie.ui.home.HomeViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private var dataRepository = Mockito.mock(DataRepository::class.java)

    @Mock
    private lateinit var observer: Observer<List<ResultsItem>>

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(dataRepository)
    }

    @Test
    fun getTrending() {

        val dummyTrending = DataDummy.generateTrending()
        val trending = MutableLiveData<List<ResultsItem>>()
        trending.value = dummyTrending

        `when`(dataRepository.getTrending()).thenReturn(trending)
        val trendingEntities = homeViewModel.trending.value
        verify(dataRepository).getTrending()

        assertNotNull(trendingEntities)
        assertEquals(4, trendingEntities?.size)

        homeViewModel.trending.observeForever(observer)
        verify(observer).onChanged(dummyTrending)
    }

//    @Test
//    fun getPopular() {
//        val popular = MutableLiveData<List<ResultsItem>>()
//        popular.value = DataDummy.generatePopular()
//        `when`(dataRepository.getPopular()).thenReturn(popular)
//        val observer = Mockito.mock(Observer::class.java)
//        homeViewModel.trending.observeForever(observer as Observer<List<ResultsItem>>)
//        Mockito.verify(dataRepository).getPopular()
//    }
}