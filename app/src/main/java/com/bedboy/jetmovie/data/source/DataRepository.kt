package com.bedboy.jetmovie.data.source

import androidx.lifecycle.LiveData
import com.bedboy.jetmovie.data.NetworkBoundResource
import com.bedboy.jetmovie.data.source.local.LocalDataSource
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.data.source.local.entity.GenreEntity
import com.bedboy.jetmovie.data.source.local.entity.VideoEntity
import com.bedboy.jetmovie.data.source.remote.ApiResponse
import com.bedboy.jetmovie.data.source.remote.RemoteDataSource
import com.bedboy.jetmovie.data.source.remote.response.ResultsGenre
import com.bedboy.jetmovie.data.source.remote.response.ResultsItem
import com.bedboy.jetmovie.data.source.remote.response.ResultsVideos
import com.bedboy.jetmovie.utils.AppExecutors
import com.bedboy.jetmovie.vo.Resource

class DataRepository private constructor(
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
    private val remoteDataSource: RemoteDataSource
) :
    DataSource {

    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(
                    localDataSource,
                    appExecutors,
                    remoteData
                ).apply { instance = this }
            }
    }

    override fun getTrending(): LiveData<Resource<List<DataMovieTVEntity>>> {
        return object :
            NetworkBoundResource<List<DataMovieTVEntity>, List<ResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<DataMovieTVEntity>> =
                localDataSource.getTrending()

            override fun shouldFetch(data: List<DataMovieTVEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllTrending()

            override fun saveCallResult(data: List<ResultsItem>) {
                val listTrending = ArrayList<DataMovieTVEntity>()

                for (response in data) {
                    with(response) {
                        val trending = DataMovieTVEntity(
                            id,
                            posterPath,
                            title,
                            voteAverage,
                            genreIds.toString(),
                            name,
                            mediaType,
                            backdropPath,
                            overview
                        )
                        listTrending.add(trending)
                    }
                }
                localDataSource.insertTrending(listTrending)
            }
        }.asLiveData()
    }

    override fun getPopular(): LiveData<Resource<List<DataMovieTVEntity>>> {
        return object :
            NetworkBoundResource<List<DataMovieTVEntity>, List<ResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<DataMovieTVEntity>> =
                localDataSource.getPopular()

            override fun shouldFetch(data: List<DataMovieTVEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllPopular()

            override fun saveCallResult(data: List<ResultsItem>) {
                val listPopular = ArrayList<DataMovieTVEntity>()
                for (response in data) {
                    with(response) {
                        val popular = DataMovieTVEntity(
                            id,
                            posterPath,
                            title,
                            voteAverage,
                            genreIds.toString(),
                            name,
                            mediaType,
                            backdropPath,
                            overview
                        )
                        listPopular.add(popular)
                    }
                }
                localDataSource.insertPopular(listPopular)
            }
        }.asLiveData()

    }

    override fun getVideoDetail(
        media_type: String,
        id: String
    ): LiveData<Resource<List<VideoEntity>>> {
        return object : NetworkBoundResource<List<VideoEntity>, List<ResultsVideos>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<VideoEntity>> =
                localDataSource.getVideo(id)

            override fun shouldFetch(data: List<VideoEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsVideos>>> =
                remoteDataSource.getDetailVideos(media_type, id)

            override fun saveCallResult(data: List<ResultsVideos>) {
                val listVideo = ArrayList<VideoEntity>()
                for (response in data) {
                    with(response) {
                        val video = VideoEntity(
                            id,
                            key
                        )
                        listVideo.add(video)
                    }
                }
                localDataSource.insertVideo(listVideo)
            }
        }.asLiveData()
    }

    override fun getGenre(media_type: String): LiveData<Resource<List<GenreEntity>>> {
        return object : NetworkBoundResource<List<GenreEntity>, List<ResultsGenre>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<GenreEntity>> =
                localDataSource.getGenre()

            override fun shouldFetch(data: List<GenreEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsGenre>>> =
                remoteDataSource.getAllGenre(media_type)

            override fun saveCallResult(data: List<ResultsGenre>) {
                val listGenre = ArrayList<GenreEntity>()
                for (response in data) {
                    with(response) {
                        val genre = GenreEntity(
                            id,
                            name
                        )
                        listGenre.add(genre)
                    }
                }
                localDataSource.insertGenre(listGenre)
            }
        }.asLiveData()
    }

    override fun getWatchList(): LiveData<List<DataMovieTVEntity>> =
        localDataSource.getWatchList()

    override fun setWatchList(data: DataMovieTVEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setWatchList(data, state)
        }
    }

    override fun getDetailTV(id: String): LiveData<Resource<DataMovieTVEntity>> {
        return object : NetworkBoundResource<DataMovieTVEntity, ResultsItem>(appExecutors) {
            override fun loadFromDB(): LiveData<DataMovieTVEntity> =
                localDataSource.getDetail(id)

            override fun shouldFetch(data: DataMovieTVEntity?): Boolean =
                data != null

            override fun createCall(): LiveData<ApiResponse<ResultsItem>> =
                remoteDataSource.getDetailTV(id)

            override fun saveCallResult(data: ResultsItem) {
                val listGenre = ArrayList<String>()
                for (i in data.genres?.indices!!) {
                    listGenre.add(data.genres[0].name)
                }

                val detailResult = DataMovieTVEntity(
                    id = data.id,
                    imagePath = data.posterPath,
                    title = data.title,
                    vote = data.voteAverage,
                    genre = listGenre.joinToString(),
                    name = data.name,
                    backDropPath = data.backdropPath,
                    overview = data.overview,
                    isFavorite = false
                )
                localDataSource.updateDetail(detailResult, false)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(id: String): LiveData<Resource<DataMovieTVEntity>> {
        return object : NetworkBoundResource<DataMovieTVEntity, ResultsItem>(appExecutors) {
            override fun loadFromDB(): LiveData<DataMovieTVEntity> =
                localDataSource.getDetail(id)

            override fun shouldFetch(data: DataMovieTVEntity?): Boolean =
                data != null

            override fun createCall(): LiveData<ApiResponse<ResultsItem>> =
                remoteDataSource.getDetailMovie(id)

            override fun saveCallResult(data: ResultsItem) {
                val listGenre = ArrayList<String>()
                for (i in data.genres?.indices!!) {
                    listGenre.add(data.genres[0].name)
                }

                val detailResult = DataMovieTVEntity(
                    id = data.id,
                    imagePath = data.posterPath,
                    title = data.title,
                    vote = data.voteAverage,
                    genre = listGenre.joinToString(),
                    name = data.name,
                    backDropPath = data.backdropPath,
                    overview = data.overview,
                    isFavorite = false
                )
                localDataSource.updateDetail(detailResult, false)
            }
        }.asLiveData()
    }
}