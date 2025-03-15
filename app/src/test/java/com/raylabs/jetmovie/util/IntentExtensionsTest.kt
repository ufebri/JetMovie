package com.raylabs.jetmovie.util

import android.content.Intent
import com.raylabs.jetmovie.utils.getParcelableExtraCompat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class IntentExtensionsTest {

    @Test
    fun `getParcelableExtraCompat should return parcelable when API less than Tiramisu`() {
        val intent = Mockito.mock(Intent::class.java)
        val key = "parcel_key"
        val expectedParcelable = TestParcelable("Test Data")

        // Simulasi Android 12- (Pre-TIRAMISU)
        Mockito.`when`(intent.getParcelableExtra<TestParcelable>(key))
            .thenReturn(expectedParcelable)

        val result: TestParcelable? = intent.getParcelableExtraCompat(key)

        assertEquals(expectedParcelable, result)
    }

    @Test
    fun `getParcelableExtraCompat should return null if no parcelable found`() {
        val intent = Mockito.mock(Intent::class.java)
        val key = "parcel_key"

        // Simulasi tidak ada parcelable yang ditemukan
        Mockito.`when`(intent.getParcelableExtra<TestParcelable>(key))
            .thenReturn(null)

        val result: TestParcelable? = intent.getParcelableExtraCompat(key)

        assertNull(result)
    }
}
