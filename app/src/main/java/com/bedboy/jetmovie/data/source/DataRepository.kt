package com.bedboy.jetmovie.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bedboy.jetmovie.data.source.remote.RemoteDataSource
import com.bedboy.jetmovie.data.source.remote.response.ResultsItem
import com.bedboy.jetmovie.data.source.remote.response.ResultsVideos

class DataRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    DataSource {

    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(remoteData: RemoteDataSource): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(remoteData).apply { instance = this }
            }
    }

    override fun getTrending(): LiveData<List<ResultsItem>> {
        val dataTrending = MutableLiveData<List<ResultsItem>>()
        remoteDataSource.getAllTrending(object : RemoteDataSource.LoadHomeDataCallback {
            override fun onAllHomeDataReceived(homeResponse: List<ResultsItem>?) {
                dataTrending.postValue(homeResponse)
            }

        })
        return dataTrending
    }

    override fun getPopular(): LiveData<List<ResultsItem>> {
        val dataPopular = MutableLiveData<List<ResultsItem>>()
        remoteDataSource.getAllPopular(object : RemoteDataSource.LoadHomeDataCallback {
            override fun onAllHomeDataReceived(homeResponse: List<ResultsItem>?) {
                dataPopular.postValue(homeResponse)
            }
        })
        return dataPopular
    }

    override fun getVideoDetail(media_type: String, id: String): LiveData<List<ResultsVideos>> {
        val dataVideos = MutableLiveData<List<ResultsVideos>>()
        remoteDataSource.getDetailVideos(object : RemoteDataSource.LoadVideosCallback {
            override fun onAllVideosReceived(videoDetailResponse: List<ResultsVideos>?) {
                dataVideos.postValue(videoDetailResponse)
            }
        })
        return dataVideos
    }
}