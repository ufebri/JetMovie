package com.bedboy.jetmovie.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    var id: String,
    var title: String,
    var rating: String,
    var sinopsis: String,
    var releaseDate: String,
    var imagePath: String
) : Parcelable
