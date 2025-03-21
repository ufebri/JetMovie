package com.raylabs.jetmovie.utils

import com.raylabs.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.data.source.remote.response.ResultsItem
import com.raylabs.jetmovie.utils.DataHelper.toMillisAt10AM

object DataMapper {

    fun toListEntities(
        listResponse: List<ResultsItem>,
        sourceData: String
    ): List<DataMovieTVEntity> {
        val entitiesList = ArrayList<DataMovieTVEntity>()
        listResponse.map {
            val mData = DataMovieTVEntity(
                id = it.id,
                title = it.title ?: it.name, //title for movie, name for tv
                vote = it.voteAverage,
                genre = DataHelper.convertGenre(it.genreIds),
                media_type = it.mediaType,
                backDropPath = it.backdropPath,
                imagePath = it.posterPath,
                overview = it.overview,
                dataFrom = sourceData,
                releaseData = it.releaseDate?.toMillisAt10AM() ?: it.firstAirDate?.toMillisAt10AM()
            )
            entitiesList.add(mData)
        }
        return entitiesList
    }
}