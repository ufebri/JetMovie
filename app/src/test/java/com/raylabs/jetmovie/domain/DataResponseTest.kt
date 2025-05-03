package com.raylabs.jetmovie.domain

import com.raylabs.jetmovie.data.source.remote.response.*
import org.junit.Assert.*
import org.junit.Test

class DataResponseTest {

    @Test
    fun `create ResultsItem and verify fields`() {
        val genreList = listOf(ResultsGenre(28, "Action"), ResultsGenre(12, "Adventure"))
        val resultsItem = ResultsItem(
            id = "101",
            posterPath = "/poster.jpg",
            title = "Jet Movie",
            voteAverage = 9.2,
            genreIds = listOf(28, 12),
            name = null,
            mediaType = "movie",
            backdropPath = "/backdrop.jpg",
            overview = "Overview of Jet Movie",
            genres = genreList,
            releaseDate = "2025-04-01",
            firstAirDate = null
        )

        assertEquals("101", resultsItem.id)
        assertEquals("/poster.jpg", resultsItem.posterPath)
        assertEquals("Jet Movie", resultsItem.title)
        assertEquals(9.2, resultsItem.voteAverage, 0.01)
        assertEquals(listOf(28, 12), resultsItem.genreIds)
        assertEquals("movie", resultsItem.mediaType)
        assertEquals("/backdrop.jpg", resultsItem.backdropPath)
        assertEquals("Overview of Jet Movie", resultsItem.overview)
        assertEquals(2, resultsItem.genres?.size)
        assertEquals("Action", resultsItem.genres?.get(0)?.name)
        assertEquals("Adventure", resultsItem.genres?.get(1)?.name)
        assertEquals("2025-04-01", resultsItem.releaseDate)
    }

    @Test
    fun `create DataResponse and verify contents`() {
        val item = ResultsItem(
            id = "101",
            posterPath = "/poster.jpg",
            title = "Jet Movie",
            voteAverage = 9.2,
            genreIds = listOf(28),
            name = null,
            mediaType = "movie",
            backdropPath = "/backdrop.jpg",
            overview = "A sample movie",
            genres = null,
            releaseDate = "2025-01-01",
            firstAirDate = null
        )

        val response = DataResponse(
            page = 1,
            totalPages = 10,
            results = listOf(item),
            totalResults = 100
        )

        assertEquals(1, response.page)
        assertEquals(10, response.totalPages)
        assertEquals(1, response.results.size)
        assertEquals(100, response.totalResults)
        assertEquals("Jet Movie", response.results[0].title)
    }
}
