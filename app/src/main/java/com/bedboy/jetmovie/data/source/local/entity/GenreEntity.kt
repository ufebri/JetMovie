package com.bedboy.jetmovie.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreEntity(
    var id: Int,
    var name: String
) : Parcelable