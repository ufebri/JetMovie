package com.raylabs.jetmovie.domain

import com.raylabs.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.data.source.local.entity.displayRating
import com.raylabs.jetmovie.data.source.local.entity.primaryGenre
import com.raylabs.jetmovie.data.source.local.entity.shouldShowReleaseDate
import org.junit.Assert.*
import org.junit.Test

class DataMovieTVEntityTest {

    private fun dummyEntity(
        vote: Double? = null,
        genre: String? = null,
        dataFrom: String? = null
    ) = DataMovieTVEntity(
        id = "1",
        imagePath = null,
        title = null,
        vote = vote,
        genre = genre,
        mediaType = null,
        backDropPath = null,
        overview = null,
        isFavorite = false,
        dataFrom = dataFrom,
        releaseData = null
    )


    @Test
    fun `create entity and verify fields`() {
        val entity = DataMovieTVEntity(
            id = "123",
            imagePath = "/poster.jpg",
            title = "Jet Movie",
            vote = 8.5,
            genre = "Action, Sci-Fi",
            mediaType = "movie",
            backDropPath = "/backdrop.jpg",
            overview = "A test movie overview.",
            isFavorite = true,
            dataFrom = "local",
            releaseData = "2025-01-01"
        )

        assertEquals("123", entity.id)
        assertEquals("/poster.jpg", entity.imagePath)
        assertEquals("Jet Movie", entity.title)
        assertEquals(8.5, entity.vote ?: 0.0, 0.01)
        assertEquals("Action, Sci-Fi", entity.genre)
        assertEquals("movie", entity.mediaType)
        assertEquals("/backdrop.jpg", entity.backDropPath)
        assertEquals("A test movie overview.", entity.overview)
        assertTrue(entity.isFavorite)
        assertEquals("local", entity.dataFrom)
        assertEquals("2025-01-01", entity.releaseData)
    }

    @Test
    fun `update favorite flag`() {
        val entity = DataMovieTVEntity(id = "321", imagePath = null, title = null, vote = null, genre = null, mediaType = null, backDropPath = null, overview = null)
        assertFalse(entity.isFavorite)
        entity.isFavorite = true
        assertTrue(entity.isFavorite)
    }

    @Test
    fun `displayRating should convert vote correctly`() {
        val entity = dummyEntity(vote = 8.0)
        assertEquals(4.0f, entity.displayRating())
    }

    @Test
    fun `displayRating should return 0 for null vote`() {
        val entity = dummyEntity(vote = null)
        assertEquals(0.0f, entity.displayRating())
    }

    @Test
    fun `primaryGenre should return first genre`() {
        val entity = dummyEntity(genre = "Action,Comedy,Drama")
        assertEquals("Action", entity.primaryGenre())
    }

    @Test
    fun `primaryGenre should return null for null genre`() {
        val entity = dummyEntity(genre = null)
        assertNull(entity.primaryGenre())
    }

    @Test
    fun `shouldShowReleaseDate should return true for 'upcoming'`() {
        val entity = dummyEntity(dataFrom = "upcoming")
        assertTrue(entity.shouldShowReleaseDate())
    }

    @Test
    fun `shouldShowReleaseDate should return false for other value`() {
        val entity = dummyEntity(dataFrom = "popular")
        assertFalse(entity.shouldShowReleaseDate())
    }

    @Test
    fun `shouldShowReleaseDate should ignore case`() {
        val entity = dummyEntity(dataFrom = "UpComIng")
        assertTrue(entity.shouldShowReleaseDate())
    }
}
