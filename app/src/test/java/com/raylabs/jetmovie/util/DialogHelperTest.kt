package com.raylabs.jetmovie.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.raylabs.jetmovie.utils.DialogHelper
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class DialogHelperTest {

    private lateinit var context: Context
    private lateinit var builder: AlertDialog.Builder

    @Before
    fun setUp() {
        context = mock(Context::class.java)
        builder = mock(AlertDialog.Builder::class.java)

        // Mock method chaining
        `when`(builder.setTitle(anyString())).thenReturn(builder)
        `when`(builder.setMessage(anyString())).thenReturn(builder)
        `when`(builder.setPositiveButton(anyString(), any())).thenReturn(builder)
        `when`(builder.setNegativeButton(anyString(), any())).thenReturn(builder)
        `when`(builder.show()).thenReturn(mock(AlertDialog::class.java))

        // Inject builder provider
        DialogHelper.builderProvider = { context -> builder }
    }

    @After
    fun tearDown() {
        DialogHelper.builderProvider = null
    }

    @Test
    fun testShowDialog_withPositiveAndNegativeButton() {
        DialogHelper.showDialog(
            context = context,
            title = "Test Title",
            message = "Test Message",
            positiveText = "Yes",
            negativeText = "No"
        )

        verify(builder).setTitle("Test Title")
        verify(builder).setMessage("Test Message")
        verify(builder).setPositiveButton(eq("Yes"), any())
        verify(builder).setNegativeButton(eq("No"), any())
        verify(builder).show()
    }

    @Test
    fun testShowDialog_withOnlyPositiveButton() {
        DialogHelper.showDialog(
            context = context,
            title = "Test Title",
            message = "Test Message"
        )

        verify(builder).setTitle("Test Title")
        verify(builder).setMessage("Test Message")
        verify(builder).setPositiveButton(eq("OK"), any())
        verify(builder, never()).setNegativeButton(anyString(), any())
        verify(builder).show()
    }
}
