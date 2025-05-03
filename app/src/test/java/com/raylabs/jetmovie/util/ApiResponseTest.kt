package com.raylabs.jetmovie.util

import com.raylabs.jetmovie.data.source.remote.ApiResponse
import com.raylabs.jetmovie.data.source.remote.StatusResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ApiResponseTest {

    @Test
    fun `success should create ApiResponse with SUCCESS status`() {
        val data = "Hello"
        val response = ApiResponse.success(data)

        assertEquals(StatusResponse.SUCCESS, response.status)
        assertEquals(data, response.body)
        assertNull(response.message)
    }

    @Test
    fun `empty should create ApiResponse with EMPTY status and message`() {
        val msg = "No data"
        val data = emptyList<String>()
        val response = ApiResponse.empty(msg, data)

        assertEquals(StatusResponse.EMPTY, response.status)
        assertEquals(data, response.body)
        assertEquals(msg, response.message)
    }

    @Test
    fun `error should create ApiResponse with ERROR status and message`() {
        val msg = "Something went wrong"
        val data = null
        val response = ApiResponse.error(msg, data)

        assertEquals(StatusResponse.ERROR, response.status)
        assertEquals(msg, response.message)
    }
}
