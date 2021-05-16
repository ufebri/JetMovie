package com.bedboy.jetmovie.data.source.remote

import android.util.Log
import com.bedboy.jetmovie.data.source.remote.response.*
import com.bedboy.jetmovie.network.ApiConfig
import com.bedboy.jetmovie.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        private val client = ApiConfig.getApiService()
        private val TAG = this::class.java.simpleName


        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }


    fun getAllTrending(callback: LoadHomeDataCallback) {
        EspressoIdlingResource.increment()
        client.getTrending().enqueue(object : Callback<DataResponse> {
            override fun onResponse(
                call: Call<DataResponse>,
                response: Response<DataResponse>
            ) {
                response.body()?.results?.let { callback.onAllHomeDataReceived(it) }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }

        })
    }

    fun getAllPopular(callback: LoadHomeDataCallback) {
        EspressoIdlingResource.increment()
        client.getPopular().enqueue(object : Callback<DataResponse> {
            override fun onResponse(
                call: Call<DataResponse>,
                response: Response<DataResponse>
            ) {
                response.body()?.results?.let { callback.onAllHomeDataReceived(it) }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getDetailVideos(media_type: String, dataID: String, callback: LoadVideosCallback) {
        EspressoIdlingResource.increment()
        client.getDetailVideo(media_type, dataID).enqueue(object : Callback<GetDetailVideos> {
            override fun onResponse(
                call: Call<GetDetailVideos>,
                response: Response<GetDetailVideos>
            ) {
                response.body()?.results?.let { callback.onAllVideosReceived(it) }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<GetDetailVideos>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }

        })

    }

    fun getAllGenre(media_type: String, callback: LoadGenreCallback) {
        EspressoIdlingResource.increment()
        client.getGenre(media_type).enqueue(object : Callback<DataGenre> {
            override fun onResponse(call: Call<DataGenre>, response: Response<DataGenre>) {
                response.body()?.genres?.let { callback.onAllGenreReceived(it) }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DataGenre>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })

    }

    //The data model for TVShow & Movies are same
    interface LoadHomeDataCallback {
        fun onAllHomeDataReceived(homeResponse: List<ResultsItem>)
    }

    interface LoadVideosCallback {
        fun onAllVideosReceived(videoDetailResponse: List<ResultsVideos>)
    }

    interface LoadGenreCallback {
        fun onAllGenreReceived(genreResponse: List<ResultsGenre>)
    }
}