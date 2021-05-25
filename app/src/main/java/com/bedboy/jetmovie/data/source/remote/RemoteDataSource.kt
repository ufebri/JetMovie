package com.bedboy.jetmovie.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bedboy.jetmovie.data.source.remote.response.*
import com.bedboy.jetmovie.network.ApiConfig
import com.bedboy.jetmovie.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        private val client = ApiConfig.getApiService()
        private const val TAG = "RemoteDataSource"


        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }


    fun getAllTrending(): LiveData<ApiResponse<List<ResultsItem>>> {
        EspressoIdlingResource.increment()
        val resultsItem = MutableLiveData<ApiResponse<List<ResultsItem>>>()
        client.getTrending().enqueue(object : Callback<DataResponse> {
            override fun onResponse(
                call: Call<DataResponse>,
                response: Response<DataResponse>
            ) {
                resultsItem.value =
                    ApiResponse.success(response.body()?.results as List<ResultsItem>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }

        })
        return resultsItem
    }

    fun getAllPopular(): LiveData<ApiResponse<List<ResultsItem>>> {
        EspressoIdlingResource.increment()
        val resultsItem = MutableLiveData<ApiResponse<List<ResultsItem>>>()
        client.getPopular().enqueue(object : Callback<DataResponse> {
            override fun onResponse(
                call: Call<DataResponse>,
                response: Response<DataResponse>
            ) {
                resultsItem.value =
                    ApiResponse.success(response.body()?.results as List<ResultsItem>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultsItem
    }

    fun getDetailVideos(
        media_type: String,
        dataID: String
    ): LiveData<ApiResponse<List<ResultsVideos>>> {
        val resultsVideos = MutableLiveData<ApiResponse<List<ResultsVideos>>>()
        EspressoIdlingResource.increment()
        client.getDetailVideo(media_type, dataID).enqueue(object : Callback<GetDetailVideos> {
            override fun onResponse(
                call: Call<GetDetailVideos>,
                response: Response<GetDetailVideos>
            ) {
                resultsVideos.value =
                    ApiResponse.success(response.body()?.results as List<ResultsVideos>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<GetDetailVideos>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }

        })
        return resultsVideos
    }

    fun getDetailTV(dataID: String): LiveData<ApiResponse<ResultsItem>> {
        EspressoIdlingResource.increment()
        val resultDetailItem = MutableLiveData<ApiResponse<ResultsItem>>()
        client.getDetailTV(dataID).enqueue(object : Callback<ResultsItem> {
            override fun onResponse(call: Call<ResultsItem>, response: Response<ResultsItem>) {
                resultDetailItem.value = ApiResponse.success(response.body() as ResultsItem)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ResultsItem>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultDetailItem
    }

    fun getDetailMovie(dataID: String): LiveData<ApiResponse<ResultsItem>> {
        EspressoIdlingResource.increment()
        val resultDetailItem = MutableLiveData<ApiResponse<ResultsItem>>()
        client.getDetailMovie(dataID).enqueue(object : Callback<ResultsItem> {
            override fun onResponse(call: Call<ResultsItem>, response: Response<ResultsItem>) {
                resultDetailItem.value = ApiResponse.success(response.body() as ResultsItem)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ResultsItem>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultDetailItem
    }

    fun getAllGenre(media_type: String): LiveData<ApiResponse<List<ResultsGenre>>> {
        val resultsGenre = MutableLiveData<ApiResponse<List<ResultsGenre>>>()
        EspressoIdlingResource.increment()
        client.getGenre(media_type).enqueue(object : Callback<DataGenre> {
            override fun onResponse(call: Call<DataGenre>, response: Response<DataGenre>) {
                resultsGenre.value =
                    ApiResponse.success(response.body()?.genres as List<ResultsGenre>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DataGenre>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultsGenre
    }
}