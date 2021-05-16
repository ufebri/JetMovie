package com.bedboy.jetmovie.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataMovieTVEntity(
    var id: String,
    var imagePath: String,
    var title: String?,
    var vote: Double,
    var genre: List<Int>,
    var name: String?,
    var media_type: String,
    var backDropPath: String,
    var overview: String
) : Parcelable
