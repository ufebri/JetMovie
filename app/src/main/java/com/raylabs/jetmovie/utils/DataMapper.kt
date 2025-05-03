package com.raylabs.jetmovie.utils

import com.raylabs.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.data.source.local.entity.GenreEntity
import com.raylabs.jetmovie.data.source.local.entity.VideoEntity
import com.raylabs.jetmovie.data.source.remote.response.ResultsGenre
import com.raylabs.jetmovie.data.source.remote.response.ResultsItem
import com.raylabs.jetmovie.data.source.remote.response.ResultsVideos

object DataMapper {

    fun toListEntities(
        listResponse: List<ResultsItem>,
        genre: String,
        sourceData: String
    ): List<DataMovieTVEntity> {
        val entitiesList = ArrayList<DataMovieTVEntity>()
        listResponse.map {
            val mData = DataMovieTVEntity(
                id = it.id,
                title = it.title ?: it.name, //title for movie, name for tv
                vote = it.voteAverage,
                genre = genre,
                mediaType = if (it.title.isNullOrEmpty()) "tv" else "movie",
                backDropPath = it.backdropPath,
                imagePath = it.posterPath,
                overview = it.overview,
                dataFrom = sourceData,
                releaseData = it.releaseDate ?: it.firstAirDate
            )
            entitiesList.add(mData)
        }
        return entitiesList
    }

    fun toDataEntities(data: ResultsItem, sourceData: String): DataMovieTVEntity {
        val listGenre = data.genres?.map { it.name }.orEmpty()
        return DataMovieTVEntity(
            id = data.id,
            vote = data.voteAverage,
            genre = listGenre.joinToString(),
            overview = data.overview,
            isFavorite = false,
            backDropPath = data.backdropPath,
            imagePath = data.posterPath,
            title = data.title ?: data.name,
            mediaType = data.mediaType,
            dataFrom = sourceData,
            releaseData = data.firstAirDate
        )
    }

    fun toVideoEntity(id: String, response: List<ResultsVideos>): List<VideoEntity> {
        val mData = ArrayList<VideoEntity>()
        response.map {
            val mVideo = VideoEntity(
                id = id,
                key = it.key
            )
            mData.add(mVideo)
        }
        return mData
    }

    fun toGenreEntity(response: List<ResultsGenre>?): List<GenreEntity> {
        val mData = ArrayList<GenreEntity>()
        response?.map {
            val mGenre = GenreEntity(
                id = it.id,
                name = it.name
            )
            mData.add(mGenre)
        }
        return mData
    }

    fun convertGenre(mDataList: List<GenreEntity>, genreID: List<Int>): String {
        val filteredGenre = ArrayList<GenreEntity>()
        for (id in genreID) {
            val genre = mDataList.find { it.id == id }
            if (genre != null)
                filteredGenre.add(genre)
        }
        return filteredGenre.joinToString { it.name }
    }
}