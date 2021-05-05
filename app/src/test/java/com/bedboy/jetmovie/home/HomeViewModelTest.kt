package com.bedboy.jetmovie.home

import com.bedboy.jetmovie.ui.home.HomeViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel()
    }

    @Test
    fun getMovies() {
        val movies = homeViewModel.getMovies()
        assertNotNull(movies)
        assertEquals(10, movies.size)
    }

    @Test
    fun getTVShow() {
        val tvShows = homeViewModel.getTVShow()
        assertNotNull(tvShows)
        assertEquals(10, tvShows.size)
    }
}