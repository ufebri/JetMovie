package com.raylabs.jetmovie.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Trending Movies & TVShow Model
 * - media_type field is a key
 * - set page 1 for Trending Section
 * - set page 2 for Popular Section
 */

@Parcelize
data class DataResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<ResultsItem>,

    @field:SerializedName("total_results")
    val totalResults: Int
) : Parcelable

@Parcelize
data class ResultsItem(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("title")
    val title: String?,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("genre_ids")
    val genreIds: List<Int>,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("media_type")
    val mediaType: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("genres")
    val genres: List<ResultsGenre>?,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null

) : Parcelable

/**
 * Detail Videos Model
 * - type field is a key
 */

@Parcelize
data class GetDetailVideos(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("results")
    val results: List<ResultsVideos>
) : Parcelable

@Parcelize
data class ResultsVideos(

    @field:SerializedName("site")
    val site: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("key")
    val key: String
) : Parcelable

/**
 * Genre
 *  - The ID probably updated in the future
 * */

@Parcelize
data class DataGenre(

    @field:SerializedName("genres")
    val genres: List<ResultsGenre>
) : Parcelable

@Parcelize
data class ResultsGenre(
    @field:SerializedName("id")
    var id: Int,

    @field:SerializedName("name")
    var name: String
) : Parcelable
