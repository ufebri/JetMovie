package com.bedboy.jetmovie.data.source

import com.bedboy.jetmovie.data.source.local.entity.GenreEntity

interface GenreDataSource {

    fun getGenre(): List<GenreEntity>
}