package com.bedboy.jetmovie.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bedboy.jetmovie.data.source.DataRepository
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
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
class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<List<DataMovieTVEntity>>

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(dataRepository)
    }

    @Test
    fun getTrending() {

        val dummyTrending = DataDummy.generateData()
        val trending = MutableLiveData<List<DataMovieTVEntity>>()
        trending.value = dummyTrending

        `when`(dataRepository.getTrending()).thenReturn(trending)
        val trendingEntities = homeViewModel.trending().value
        verify(dataRepository).getTrending()

        assertNotNull(trendingEntities)
        assertEquals(20, trendingEntities?.size)

        homeViewModel.trending().observeForever(observer)
        verify(observer).onChanged(dummyTrending)
    }

    @Test
    fun getPopular() {
        val dummyPopular = DataDummy.generateData()
        val popular = MutableLiveData<List<DataMovieTVEntity>>()
        popular.value = dummyPopular

        `when`(dataRepository.getPopular()).thenReturn(popular)
        val popularEntities = homeViewModel.popular().value
        verify(dataRepository).getPopular()

        assertNotNull(popularEntities)
        assertEquals(20, popularEntities?.size)

        homeViewModel.popular().observeForever(observer)
        verify(observer).onChanged(dummyPopular)
    }
}