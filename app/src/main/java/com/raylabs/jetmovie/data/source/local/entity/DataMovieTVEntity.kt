package com.raylabs.jetmovie.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Fts4
@Entity(tableName = "dataMovieTVEntities")
data class DataMovieTVEntity(
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

    @ColumnInfo(name = "media_type")
    var media_type: String?,

    @ColumnInfo(name = "backDropPath")
    var backDropPath: String?,

    @ColumnInfo(name = "overview")
    var overview: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,

    @ColumnInfo(name = "dataFrom")
    var dataFrom: String? = "",

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid")
    var rowid: Int = 0,

    @ColumnInfo(name = "release_data")
    var releaseData: Long? = null,
) : Parcelable
