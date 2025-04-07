package com.raylabs.jetmovie.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.raylabs.jetmovie.core.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.core.data.source.local.entity.GenreEntity
import com.raylabs.jetmovie.vo.Resource
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
    private val dummyGenre = Resource.success(DataDummy.generateGenre())

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<DataMovieTVEntity>>>

    @Mock
    private lateinit var observerGenre: Observer<Resource<List<GenreEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<DataMovieTVEntity>

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(dataRepository)
    }

    @Test
    fun getTrending() {

        val dummyTrending = Resource.success(pagedList)
        `when`(dummyTrending.data?.size).thenReturn(20)

        val trending = MutableLiveData<Resource<PagedList<DataMovieTVEntity>>>()
        trending.value = dummyTrending

        `when`(dataRepository.getTrending()).thenReturn(trending)
        val trendingEntities = homeViewModel.trending().value?.data
        verify(dataRepository).getTrending()

        assertNotNull(trendingEntities)
        assertEquals(20, trendingEntities?.size)

        homeViewModel.trending().observeForever(observer)
        verify(observer).onChanged(dummyTrending)
    }

    @Test
    fun getPopular() {
        val dummyPopular = Resource.success(pagedList)
        `when`(dummyPopular.data?.size).thenReturn(20)

        val popular = MutableLiveData<Resource<PagedList<DataMovieTVEntity>>>()
        popular.value = dummyPopular

        `when`(dataRepository.getPopular()).thenReturn(popular)
        val popularEntities = homeViewModel.popular().value?.data
        verify(dataRepository).getPopular()

        assertNotNull(popularEntities)
        assertEquals(20, popularEntities?.size)

        homeViewModel.popular().observeForever(observer)
        verify(observer).onChanged(dummyPopular)
    }

    @Test
    fun getGenreMovie() {
        val genre = MutableLiveData<Resource<List<GenreEntity>>>()
        genre.value = dummyGenre

        `when`(dataRepository.getGenre("movie")).thenReturn(genre)
        val genreEntity = homeViewModel.genre("movie").value
        verify(dataRepository).getGenre("movie")

        assertNotNull(genreEntity)
        assertEquals(5, dummyGenre.data?.size)

        homeViewModel.genre("movie").observeForever(observerGenre)
        verify(observerGenre).onChanged(dummyGenre)
    }

    @Test
    fun getGenreTVShow() {
        val genre = MutableLiveData<Resource<List<GenreEntity>>>()
        genre.value = dummyGenre

        `when`(dataRepository.getGenre("tv")).thenReturn(genre)
        val genreEntity = homeViewModel.genre("tv").value
        verify(dataRepository).getGenre("tv")

        assertNotNull(genreEntity)
        assertEquals(5, dummyGenre.data?.size)

        homeViewModel.genre("tv").observeForever(observerGenre)
        verify(observerGenre).onChanged(dummyGenre)
    }
}