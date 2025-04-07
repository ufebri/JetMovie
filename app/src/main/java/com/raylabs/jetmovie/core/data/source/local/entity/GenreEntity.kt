package com.raylabs.jetmovie.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class GenreEntity(
    @PrimaryKey
    var id: Int,
    var name: String
) : Parcelable
