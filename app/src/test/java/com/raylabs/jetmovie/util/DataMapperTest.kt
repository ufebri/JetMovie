package com.raylabs.jetmovie.util

import com.raylabs.jetmovie.data.source.local.entity.GenreEntity
import com.raylabs.jetmovie.data.source.remote.response.ResultsGenre
import com.raylabs.jetmovie.data.source.remote.response.ResultsItem
import com.raylabs.jetmovie.data.source.remote.response.ResultsVideos
import com.raylabs.jetmovie.utils.DataMapper
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DataMapperTest {

    @Test
    fun `toListEntities should map list of ResultsItem to DataMovieTVEntity`() {
        val response = listOf(
            ResultsItem(
                id = "1",
                title = "Movie Title",
                voteAverage = 8.5,
                name = null,
                backdropPath = "/backdrop.jpg",
                posterPath = "/poster.jpg",
                overview = "Overview here",
                releaseDate = "2024-01-01",
                firstAirDate = null,
                genreIds = listOf(),
                mediaType = "api",
                genres = listOf(ResultsGenre(1, "Drama"), ResultsGenre(2, "Thriller"))
            )
        )

        val result = DataMapper.toListEntities(response, "Action", "remote")

        assertEquals(1, result.size)
        assertEquals("Movie Title", result[0].title)
        assertEquals("Action", result[0].genre)
        assertEquals("remote", result[0].dataFrom)
        assertEquals("movie", result[0].mediaType)
    }

    @Test
    fun `toDataEntities should map ResultsItem to DataMovieTVEntity`() {
        val data = ResultsItem(
            id = "2",
            title = null,
            name = "TV Show",
            voteAverage = 7.8,
            overview = "TV Overview",
            genres = listOf(ResultsGenre(1, "Drama"), ResultsGenre(2, "Thriller")),
            posterPath = "/tv.jpg",
            backdropPath = "/backtv.jpg",
            mediaType = "tv",
            firstAirDate = "2023-05-10",
            genreIds = listOf(1, 2)
        )

        val result = DataMapper.toDataEntities(data, "remote")

        assertEquals("TV Show", result.title)
        assertEquals("Drama, Thriller", result.genre)
        assertEquals("remote", result.dataFrom)
        assertEquals("tv", result.mediaType)
    }

    @Test
    fun `toVideoEntity should map ResultsVideos to VideoEntity`() {
        val videos = listOf(
            ResultsVideos(key = "abc123", site = "fake", name = "fake", type = "fake"),
            ResultsVideos(key = "def456", site = "fake", name = "fake", type = "fake")
        )

        val result = DataMapper.toVideoEntity("movie_1", videos)

        assertEquals(2, result.size)
        assertEquals("abc123", result[0].key)
        assertEquals("movie_1", result[1].id)
    }

    @Test
    fun `toGenreEntity should map ResultsGenre to GenreEntity`() {
        val genres = listOf(
            ResultsGenre(id = 1, name = "Action"),
            ResultsGenre(id = 2, name = "Comedy")
        )

        val result = DataMapper.toGenreEntity(genres)

        assertEquals(2, result.size)
        assertEquals("Action", result[0].name)
        assertEquals(2, result[1].id)
    }

    @Test
    fun `convertGenre should return genre names for given IDs`() {
        val genreEntities = listOf(
            GenreEntity(id = 1, name = "Action"),
            GenreEntity(id = 2, name = "Horror"),
            GenreEntity(id = 3, name = "Comedy")
        )

        val genreIds = listOf(1, 3)

        val result = DataMapper.convertGenre(genreEntities, genreIds)

        assertEquals("Action, Comedy", result)
    }

}