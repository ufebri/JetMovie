package com.raylabs.jetmovie.utils

import android.content.Intent
import android.os.Build
import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil

inline fun <reified T : Parcelable> Intent.getParcelableExtraCompat(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelableExtra(key)
    }
}

inline fun <T : Any> createDiffCallback(
    crossinline idSelector: (T) -> Any,
    crossinline contentEquality: (T, T) -> Boolean = { old, new -> old == new }
): DiffUtil.ItemCallback<T> = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = idSelector(oldItem) == idSelector(newItem)
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = contentEquality(oldItem, newItem)
}

