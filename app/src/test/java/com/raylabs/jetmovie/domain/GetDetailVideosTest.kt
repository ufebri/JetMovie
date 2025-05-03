package com.raylabs.jetmovie.domain

import com.raylabs.jetmovie.data.source.remote.response.GetDetailVideos
import com.raylabs.jetmovie.data.source.remote.response.ResultsVideos
import org.junit.Assert.*
import org.junit.Test

class GetDetailVideosTest {

    @Test
    fun `create GetDetailVideos and verify contents`() {
        val video = ResultsVideos(
            site = "YouTube",
            name = "Official Trailer",
            type = "Trailer",
            key = "abcdef123456"
        )

        val detailVideos = GetDetailVideos(
            id = 999,
            results = listOf(video)
        )

        assertEquals(999, detailVideos.id)
        assertEquals(1, detailVideos.results.size)
        assertEquals("YouTube", detailVideos.results[0].site)
        assertEquals("Official Trailer", detailVideos.results[0].name)
    }
}
