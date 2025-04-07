package com.raylabs.jetmovie.core.data.mapper

import com.raylabs.jetmovie.core.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.core.data.source.local.entity.mapToDomain
import com.raylabs.jetmovie.core.data.source.remote.response.ResultsItem
import com.raylabs.jetmovie.core.data.source.remote.response.mapToDomain
import com.raylabs.jetmovie.core.data.source.remote.response.mapToEntity
import com.raylabs.jetmovie.core.domain.model.MoviesTV

object MovieTVMapper {

    fun mapResponseToEntity(
        response: List<ResultsItem>,
        sourceData: String
    ): List<DataMovieTVEntity> {
        val entitiesList = ArrayList<DataMovieTVEntity>()
        response.map {
            if (it.posterPath != null) {
                val mData = it.mapToEntity(sourceData)
                entitiesList.add(mData)
            }
        }
        return entitiesList
    }

    fun mapEntityToDomain(entityList: List<DataMovieTVEntity>): List<MoviesTV> {
        val domainList = ArrayList<MoviesTV>()
        entityList.map {
            val mData = it.mapToDomain()
            domainList.add(mData)
        }
        return domainList
    }

    fun mapResponseToDomain(response: List<ResultsItem>): List<MoviesTV> {
        val responseList = ArrayList<MoviesTV>()
        response.map {
            val mData = it.mapToDomain()
            responseList.add(mData)
        }
        return responseList
    }
}