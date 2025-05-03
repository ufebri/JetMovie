package com.raylabs.jetmovie.domain

import com.raylabs.jetmovie.data.source.remote.response.DataGenre
import com.raylabs.jetmovie.data.source.remote.response.ResultsGenre
import org.junit.Assert.*
import org.junit.Test

class DataGenreTest {

    @Test
    fun `create DataGenre and verify list`() {
        val genreList = listOf(
            ResultsGenre(id = 1, name = "Action"),
            ResultsGenre(id = 2, name = "Comedy")
        )

        val dataGenre = DataGenre(genres = genreList)

        assertEquals(2, dataGenre.genres.size)
        assertEquals("Action", dataGenre.genres[0].name)
        assertEquals(2, dataGenre.genres[1].id)
    }
}
