package com.raylabs.jetmovie.domain

import com.raylabs.jetmovie.data.source.local.entity.DataMovieTVEntity
import org.junit.Assert.*
import org.junit.Test

class DataMovieTVEntityTest {

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
}
