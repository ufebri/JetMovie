package com.bedboy.jetmovie.utils

import com.bedboy.jetmovie.data.source.local.entity.GenreEntity

object DataHelper {

    var genres = ArrayList<GenreEntity>()

    fun convertGenre(genreID: List<Int>): String {
        val filteredGenre = ArrayList<GenreEntity>()
        for (id in genreID) {
            val genre = genres.find { it.id == id }
            if (genre != null)
                filteredGenre.add(genre)
        }
        return filteredGenre.joinToString { it.name }

    }

    enum class DataFrom(val value: String) {
        TRENDING("trending"),
        UPCOMING("upcoming"),
        SEARCH("search"),
        POPULAR("popular");
    }
}