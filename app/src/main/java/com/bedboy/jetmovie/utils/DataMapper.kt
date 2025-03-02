package com.bedboy.jetmovie.utils

import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.data.source.remote.response.ResultsItem
import com.bedboy.jetmovie.utils.DataHelper.toMillisAt10AM

object DataMapper {

    fun toListEntities(
        listResponse: List<ResultsItem>,
        sourceData: String
    ): List<DataMovieTVEntity> {
        val entitiesList = ArrayList<DataMovieTVEntity>()
        listResponse.map {
            val mData = DataMovieTVEntity(
                id = it.id,
                title = it.title,
                vote = it.voteAverage,
                genre = DataHelper.convertGenre(it.genreIds),
                name = it.name,
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