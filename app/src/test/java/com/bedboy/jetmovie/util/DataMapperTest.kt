package com.bedboy.jetmovie.util

import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.data.source.remote.response.ResultsItem
import com.bedboy.jetmovie.utils.DataHelper
import com.bedboy.jetmovie.utils.DataHelper.toMillisAt10AM
import com.bedboy.jetmovie.utils.DataMapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataMapperTest {

    private lateinit var mockResultsItem: ResultsItem
    private lateinit var mockResultsItemTV: ResultsItem
    private val sourceData = "API"

    @Before
    fun setup() {
        mockResultsItem = ResultsItem(
            id = "1",
            title = "Movie Title",
            voteAverage = 8.5,
            genreIds = listOf(28, 12), // Action, Adventure
            mediaType = "movie",
            backdropPath = "/backdrop.jpg",
            posterPath = "/poster.jpg",
            overview = "This is a movie overview.",
            releaseDate = "2023-01-01",
            firstAirDate = null,
            name = null,
            genres = null// Hanya untuk TV Series
        )

        mockResultsItemTV = ResultsItem(
            id = "2",
            title = null, // Untuk TV, `name` digunakan
            name = "TV Show Title",
            voteAverage = 7.5,
            genreIds = listOf(16, 35), // Animation, Comedy
            mediaType = "tv",
            backdropPath = "/backdrop_tv.jpg",
            posterPath = "/poster_tv.jpg",
            overview = "This is a TV show overview.",
            releaseDate = null,
            firstAirDate = "2023-02-01",
            genres = null// Hanya untuk TV Series
        )
    }

    @Test
    fun `toListEntities should correctly map movie data`() {
        val mockList = listOf(mockResultsItem)

        val expected = DataMovieTVEntity(
            id = mockResultsItem.id,
            title = mockResultsItem.title ?: mockResultsItem.name,
            vote = mockResultsItem.voteAverage,
            genre = DataHelper.convertGenre(mockResultsItem.genreIds),
            media_type = mockResultsItem.mediaType,
            backDropPath = mockResultsItem.backdropPath,
            imagePath = mockResultsItem.posterPath,
            overview = mockResultsItem.overview,
            dataFrom = sourceData,
            releaseData = mockResultsItem.releaseDate?.toMillisAt10AM()
        )

        val result = DataMapper.toListEntities(mockList, sourceData)

        assertEquals(1, result.size)
        assertEquals(expected, result[0])
    }

    @Test
    fun `toListEntities should correctly map TV show data`() {
        val mockList = listOf(mockResultsItemTV)

        val expected = DataMovieTVEntity(
            id = mockResultsItemTV.id,
            title = mockResultsItemTV.title ?: mockResultsItemTV.name,
            vote = mockResultsItemTV.voteAverage,
            genre = DataHelper.convertGenre(mockResultsItemTV.genreIds),
            media_type = mockResultsItemTV.mediaType,
            backDropPath = mockResultsItemTV.backdropPath,
            imagePath = mockResultsItemTV.posterPath,
            overview = mockResultsItemTV.overview,
            dataFrom = sourceData,
            releaseData = mockResultsItemTV.firstAirDate?.toMillisAt10AM()
        )

        val result = DataMapper.toListEntities(mockList, sourceData)

        assertEquals(1, result.size)
        assertEquals(expected, result[0])
    }

    @Test
    fun `toListEntities should return empty list when input is empty`() {
        val result = DataMapper.toListEntities(emptyList(), sourceData)
        assertEquals(0, result.size)
    }
}
