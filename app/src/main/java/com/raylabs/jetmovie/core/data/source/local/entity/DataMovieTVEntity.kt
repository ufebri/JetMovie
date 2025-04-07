package com.raylabs.jetmovie.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import com.raylabs.jetmovie.core.data.source.remote.response.ResultsItem
import com.raylabs.jetmovie.core.domain.model.MoviesTV
import com.raylabs.jetmovie.utils.DataHelper
import kotlinx.parcelize.Parcelize
import java.util.Locale

@Parcelize
@Fts4
@Entity(tableName = "dataMovieTVEntities")
data class DataMovieTVEntity(
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "imagePath")
    var imagePath: String,

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

    @ColumnInfo(name = "release_date")
    var releaseDate: String?,
) : Parcelable

fun DataMovieTVEntity.mapToResponse(response: ResultsItem, sourceData: String) =
    response.let {
        DataMovieTVEntity(
            id = it.id,
            title = it.title ?: it.name, //title for movie, name for tv
            vote = it.voteAverage,
            genre = DataHelper.convertGenre(it.genreIds),
            media_type = it.mediaType,
            backDropPath = it.backdropPath,
            imagePath = it.posterPath ?: "",
            overview = it.overview,
            dataFrom = sourceData,
            releaseDate = it.releaseDate ?: it.firstAirDate
        )
    }

fun DataMovieTVEntity.mapToDomain() = this.let {
    val mVote: Double = (it.vote ?: 0.0) / 10.0 * 5.0
    MoviesTV(
        id = it.id,
        title = it.title ?: "",
        posterPath = it.imagePath,
        backdropPath = it.backDropPath ?: "",
        rating = String.format(Locale.getDefault(), "%.1f", it.vote),
        ratingBar = String.format(Locale.getDefault(), "%.1f", mVote).toFloat(),
        genre = it.genre ?: "",
        releaseDate = it.releaseDate ?: ""
    )
}