package com.bedboy.jetmovie.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "dataMovieTVEntities")
data class DataMovieTVEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "imagePath")
    var imagePath: String?,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "vote")
    var vote: Double?,

    @ColumnInfo(name = "genre")
    var genre: String?,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "media_type")
    var media_type: String?,

    @ColumnInfo(name = "backDropPath")
    var backDropPath: String?,

    @ColumnInfo(name = "overview")
    var overview: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable
