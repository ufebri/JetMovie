package com.raylabs.jetmovie.util

import com.raylabs.jetmovie.utils.DataHelper.toMillisAt10AM
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DateTest {

    @Test
    fun `test valid date string converts to millis at 10AM`() {
        // Arrange: valid date string
        val dateString = "2025-04-25"

        // Act: call toMillisAt10AM()
        val result = dateString.toMillisAt10AM()

        // Get the expected value manually
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(dateString)
        val calendar = Calendar.getInstance().apply {
            time = date
            set(Calendar.HOUR_OF_DAY, 10)  // Set to 10:00 AM
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val expectedMillis = calendar.timeInMillis

        // Assert: Check if the result matches the expected millis at 10 AM
        assertEquals(expectedMillis, result)
    }

    @Test
    fun `test invalid date string returns 0L`() {
        // Arrange: invalid date string
        val invalidDateString = "invalid-date"

        // Act: call toMillisAt10AM() with invalid date
        val result = invalidDateString.toMillisAt10AM()

        // Assert: Verify that the result is 0L for invalid input
        assertEquals(0L, result)
    }

    @Test
    fun `test empty date string returns 0L`() {
        // Arrange: empty date string
        val emptyDateString = ""

        // Act: call toMillisAt10AM() with an empty string
        val result = emptyDateString.toMillisAt10AM()

        // Assert: Verify that the result is 0L for empty input
        assertEquals(0L, result)
    }
}