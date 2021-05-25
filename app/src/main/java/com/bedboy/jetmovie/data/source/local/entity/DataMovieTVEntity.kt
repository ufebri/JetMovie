package com.bedboy.jetmovie.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class DataMovieTVEntity(
    @PrimaryKey
    @NonNull
    var id: String,
    var imagePath: String? = "",
    var title: String? = "",
    var vote: Double?,
    var genre: String? = "",
    var name: String? = "",
    var media_type: String? = "",
    var backDropPath: String? = "",
    var overview: String? = "",
    var isFavorite: Boolean = false
) : Parcelable
