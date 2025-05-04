package com.raylabs.jetmovie.util

import androidx.recyclerview.widget.DiffUtil
import com.raylabs.jetmovie.utils.createDiffCallback
import org.junit.Assert.*
import org.junit.Test

data class DummyMovie(val id: String, val title: String)

class GenericDiffCallbackTest {

    private val diffCallback: DiffUtil.ItemCallback<DummyMovie> = createDiffCallback(
        idSelector = { it.id },
        contentEquality = { old, new -> old.title == new.title }
    )

    @Test
    fun `areItemsTheSame returns true for same id`() {
        val oldItem = DummyMovie(id = "1", title = "A")
        val newItem = DummyMovie(id = "1", title = "B")

        assertTrue(diffCallback.areItemsTheSame(oldItem, newItem))
    }

    @Test
    fun `areItemsTheSame returns false for different id`() {
        val oldItem = DummyMovie(id = "1", title = "A")
        val newItem = DummyMovie(id = "2", title = "A")

        assertFalse(diffCallback.areItemsTheSame(oldItem, newItem))
    }

    @Test
    fun `areContentsTheSame returns true for same title`() {
        val oldItem = DummyMovie(id = "1", title = "A")
        val newItem = DummyMovie(id = "1", title = "A")

        assertTrue(diffCallback.areContentsTheSame(oldItem, newItem))
    }

    @Test
    fun `areContentsTheSame returns false for different title`() {
        val oldItem = DummyMovie(id = "1", title = "A")
        val newItem = DummyMovie(id = "1", title = "B")

        assertFalse(diffCallback.areContentsTheSame(oldItem, newItem))
    }
}
