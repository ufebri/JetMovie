package com.bedboy.jetmovie.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.bedboy.jetmovie.data.source.remote.response.*
import com.bedboy.jetmovie.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000
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
        handler.postDelayed({
            client.getTrending().enqueue(object : Callback<DataResponse> {
                override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
                ) {
                    response.body()?.results?.let { callback.onAllHomeDataReceived(it) }
                }

                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }

            })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllPopular(callback: LoadHomeDataCallback) {
        handler.postDelayed({
            client.getPopular().enqueue(object : Callback<DataResponse> {
                override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
                ) {
                    response.body()?.results?.let { callback.onAllHomeDataReceived(it) }
                }

                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getDetailVideos(media_type: String, dataID: String, callback: LoadVideosCallback) {
        handler.postDelayed({
            client.getDetailVideo(media_type, dataID).enqueue(object : Callback<GetDetailVideos> {
                override fun onResponse(
                    call: Call<GetDetailVideos>,
                    response: Response<GetDetailVideos>
                ) {
                    response.body()?.results?.let { callback.onAllVideosReceived(it) }
                }

                override fun onFailure(call: Call<GetDetailVideos>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }

            })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllGenre(media_type: String, callback: LoadGenreCallback) {
        handler.postDelayed({
            client.getGenre(media_type).enqueue(object : Callback<DataGenre> {
                override fun onResponse(call: Call<DataGenre>, response: Response<DataGenre>) {
                    response.body()?.genres?.let { callback.onAllGenreReceived(it) }
                }

                override fun onFailure(call: Call<DataGenre>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
        }, SERVICE_LATENCY_IN_MILLIS)
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