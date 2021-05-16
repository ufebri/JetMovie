package com.bedboy.jetmovie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bedboy.jetmovie.data.source.DataSource
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.data.source.local.entity.GenreEntity
import com.bedboy.jetmovie.data.source.local.entity.VideoEntity
import com.bedboy.jetmovie.data.source.remote.RemoteDataSource
import com.bedboy.jetmovie.data.source.remote.response.ResultsGenre
import com.bedboy.jetmovie.data.source.remote.response.ResultsItem
import com.bedboy.jetmovie.data.source.remote.response.ResultsVideos

class FakeDataRepository(private val remoteDataSource: RemoteDataSource) :
    DataSource {

    override fun getTrending(): LiveData<List<DataMovieTVEntity>> {
        val dataTrending = MutableLiveData<List<DataMovieTVEntity>>()
        remoteDataSource.getAllTrending(object : RemoteDataSource.LoadHomeDataCallback {
            override fun onAllHomeDataReceived(homeResponse: List<ResultsItem>) {
                val listTrending = ArrayList<DataMovieTVEntity>()
                for (response in homeResponse) {
                    with(response) {
                        val trending = DataMovieTVEntity(
                            id,
                            posterPath,
                            title,
                            voteAverage,
                            genreIds,
                            name,
                            mediaType,
                            backdropPath,
                            overview
                        )
                        listTrending.add(trending)
                    }
                }
                dataTrending.postValue(listTrending)
            }

        })
        return dataTrending
    }

    override fun getPopular(): LiveData<List<DataMovieTVEntity>> {
        val dataPopular = MutableLiveData<List<DataMovieTVEntity>>()
        remoteDataSource.getAllPopular(object : RemoteDataSource.LoadHomeDataCallback {
            override fun onAllHomeDataReceived(homeResponse: List<ResultsItem>) {
                val listPopular = ArrayList<DataMovieTVEntity>()
                for (response in homeResponse) {
                    with(response) {
                        val popular = DataMovieTVEntity(
                            id,
                            posterPath,
                            title,
                            voteAverage,
                            genreIds,
                            name,
                            mediaType,
                            backdropPath,
                            overview
                        )
                        listPopular.add(popular)
                    }
                }
                dataPopular.postValue(listPopular)
            }
        })
        return dataPopular
    }

    override fun getVideoDetail(media_type: String, id: String): LiveData<List<VideoEntity>> {
        val dataVideos = MutableLiveData<List<VideoEntity>>()
        remoteDataSource.getDetailVideos(
            media_type,
            id,
            object : RemoteDataSource.LoadVideosCallback {
                override fun onAllVideosReceived(videoDetailResponse: List<ResultsVideos>) {
                    val listVideo = ArrayList<VideoEntity>()
                    for (response in videoDetailResponse) {
                        with(response) {
                            val video = VideoEntity(
                                key
                            )
                            listVideo.add(video)
                        }
                    }
                    dataVideos.postValue(listVideo)
                }
            })
        return dataVideos
    }

    override fun getGenre(media_type: String): LiveData<List<GenreEntity>> {
        val dataGenre = MutableLiveData<List<GenreEntity>>()
        remoteDataSource.getAllGenre(media_type, object : RemoteDataSource.LoadGenreCallback {
            override fun onAllGenreReceived(genreResponse: List<ResultsGenre>) {
                val listGenre = ArrayList<GenreEntity>()
                for (response in genreResponse) {
                    with(response) {
                        val genre = GenreEntity(
                            id,
                            name
                        )
                        listGenre.add(genre)
                    }
                }
                dataGenre.postValue(listGenre)
            }

        })
        return dataGenre
    }
}