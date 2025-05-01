package com.raylabs.jetmovie.util

import com.raylabs.jetmovie.data.source.local.entity.GenreEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class GenreUtilsTest {

    private val genres = listOf(
        GenreEntity(28, "Action"),
        GenreEntity(35, "Comedy"),
        GenreEntity(18, "Drama")
    )

    private fun convertGenre(genreID: List<Int>): String {
        val filteredGenre = ArrayList<GenreEntity>()
        for (id in genreID) {
            val genre = genres.find { it.id == id }
            if (genre != null)
                filteredGenre.add(genre)
        }
        return filteredGenre.joinToString { it.name }
    }

    @Test
    fun testConvertGenre_withValidIds() {
        val genreIds = listOf(28, 35)
        val result = convertGenre(genreIds)
        assertEquals("Action, Comedy", result)
    }

    @Test
    fun testConvertGenre_withUnknownId() {
        val genreIds = listOf(28, 99) // 99 tidak ada
        val result = convertGenre(genreIds)
        assertEquals("Action", result)
    }

    @Test
    fun testConvertGenre_withEmptyList() {
        val genreIds = emptyList<Int>()
        val result = convertGenre(genreIds)
        assertEquals("", result)
    }

    @Test
    fun testConvertGenre_withAllInvalidIds() {
        val genreIds = listOf(999, 888)
        val result = convertGenre(genreIds)
        assertEquals("", result)
    }

    @Test
    fun testConvertGenre_returnsCorrectStringFormat() {
        val genreIds = listOf(35, 28, 18) // Comedy, Action, Drama
        val result = convertGenre(genreIds)
        assertEquals("Comedy, Action, Drama", result)
    }
}
