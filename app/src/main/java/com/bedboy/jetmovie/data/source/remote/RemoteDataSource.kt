package com.bedboy.jetmovie.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.bedboy.jetmovie.data.source.remote.response.*
import com.bedboy.jetmovie.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiConfig: ApiConfig) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000
        private val client = ApiConfig.getApiService()
        private var media_type: String = "movie"
        private var dataID: String = "1"
        private val TAG = this::class.java.simpleName


        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiConfig: ApiConfig): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiConfig).apply { instance = this }
            }
    }


    fun getAllTrending(callback: LoadHomeDataCallback) {
        handler.postDelayed({
            client.getTrending().enqueue(object : Callback<DataResponse> {
                override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.apply {
                            results.let { callback.onAllHomeDataReceived(it) }
                            results[0].apply {
                                media_type = mediaType
                                dataID = id.toString()
                            }
                        }
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
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
                    if (response.isSuccessful) {
                        response.body()?.apply {
                            results.let { callback.onAllHomeDataReceived(it) }
                            results[0].apply {
                                media_type = mediaType
                                dataID = id.toString()
                            }
                        }
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getDetailVideos(callback: LoadVideosCallback) {
        handler.postDelayed({
            client.getDetailVideo(media_type, dataID).enqueue(object : Callback<GetDetailVideos> {
                override fun onResponse(
                    call: Call<GetDetailVideos>,
                    response: Response<GetDetailVideos>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.results.let { callback.onAllVideosReceived(it) }
                    } else {
                        Log.e(TAG, "onResponse: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<GetDetailVideos>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }

            })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllGenre(callback: LoadGenreCallback) {
        handler.postDelayed({
            client.getGenre(media_type).enqueue(object : Callback<DataGenre> {
                override fun onResponse(call: Call<DataGenre>, response: Response<DataGenre>) {
                    if (response.isSuccessful) {
                        response.body()?.genres.let { callback.onAllGenreReceived(it) }
                    } else {
                        Log.e(TAG, "onResponse: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<DataGenre>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    //The data model for TVShow & Movies are same
    interface LoadHomeDataCallback {
        fun onAllHomeDataReceived(homeResponse: List<ResultsItem>?)
    }

    interface LoadVideosCallback {
        fun onAllVideosReceived(videoDetailResponse: List<ResultsVideos>?)
    }

    interface LoadGenreCallback {
        fun onAllGenreReceived(genreResponse: List<ResultsGenre>?)
    }
}