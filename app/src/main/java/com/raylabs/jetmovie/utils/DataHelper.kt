package com.raylabs.jetmovie.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DataHelper {

    enum class DataFrom(val value: String) {
        TRENDING("trending"),
        UPCOMING("upcoming"),
        SEARCH("search"),
        POPULAR("popular"),
        DETAIL_TV("detailTV"),
        DETAIL_MOVIE("detailMOVIE");
    }

    fun String.toMillisAt10AM(): Long {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return try {
            val date = dateFormat.parse(this) ?: return 0L
            val calendar = Calendar.getInstance().apply {
                time = date
                set(Calendar.HOUR_OF_DAY, 10)  // Set to 10:00 AM
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            calendar.timeInMillis
        } catch (_: ParseException) {
            // Return 0L if the date is invalid or parsing fails
            0L
        }
    }
}