package com.raylabs.jetmovie.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.raylabs.jetmovie.core.data.source.remote.network.ApiResponse
import com.raylabs.jetmovie.core.data.source.remote.network.ApiService
import com.raylabs.jetmovie.core.data.source.remote.response.DataGenre
import com.raylabs.jetmovie.core.data.source.remote.response.DataResponse
import com.raylabs.jetmovie.core.data.source.remote.response.GetDetailVideos
import com.raylabs.jetmovie.core.data.source.remote.response.ResultsGenre
import com.raylabs.jetmovie.core.data.source.remote.response.ResultsItem
import com.raylabs.jetmovie.core.data.source.remote.response.ResultsVideos
import com.raylabs.jetmovie.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    private val mTAG: String = "RemoteDataSource"

    fun getMovieByKeyword(keyword: String): LiveData<ApiResponse<List<ResultsItem>>> {
        EspressoIdlingResource.increment()
        val resultsItem = MutableLiveData<ApiResponse<List<ResultsItem>>>()
        apiService.getMovieByKeyword(keyword = keyword).enqueue(object : Callback<DataResponse> {
            override fun onResponse(
                call: Call<DataResponse>,
                response: Response<DataResponse>
            ) {
                resultsItem.value =
                    ApiResponse.Success(response.body()?.results as List<ResultsItem>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.e(mTAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }

        })
        return resultsItem
    }

    fun getAllUpcoming(): LiveData<ApiResponse<List<ResultsItem>>> {
        EspressoIdlingResource.increment()
        val resultsItem = MutableLiveData<ApiResponse<List<ResultsItem>>>()
        apiService.getAllUpComingMovie().enqueue(object : Callback<DataResponse> {
            override fun onResponse(
                call: Call<DataResponse>,
                response: Response<DataResponse>
            ) {
                resultsItem.value =
                    ApiResponse.Success(response.body()?.results as List<ResultsItem>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.e(mTAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }

        })
        return resultsItem
    }

    fun getAllTrending(): LiveData<ApiResponse<List<ResultsItem>>> {
        EspressoIdlingResource.increment()
        val resultsItem = MutableLiveData<ApiResponse<List<ResultsItem>>>()
        apiService.getTrending().enqueue(object : Callback<DataResponse> {
            override fun onResponse(
                call: Call<DataResponse>,
                response: Response<DataResponse>
            ) {
                resultsItem.value =
                    ApiResponse.Success(response.body()?.results as List<ResultsItem>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.e(mTAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }

        })
        return resultsItem
    }

    fun getAllPopular(): LiveData<ApiResponse<List<ResultsItem>>> {
        EspressoIdlingResource.increment()
        val resultsItem = MutableLiveData<ApiResponse<List<ResultsItem>>>()
        apiService.getPopular().enqueue(object : Callback<DataResponse> {
            override fun onResponse(
                call: Call<DataResponse>,
                response: Response<DataResponse>
            ) {
                resultsItem.value =
                    ApiResponse.Success(response.body()?.results as List<ResultsItem>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.e(mTAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultsItem
    }

    fun getDetailVideos(
        mediaType: String,
        dataID: String
    ): LiveData<ApiResponse<List<ResultsVideos>>> {
        val resultsVideos = MutableLiveData<ApiResponse<List<ResultsVideos>>>()
        EspressoIdlingResource.increment()
        apiService.getDetailVideo(mediaType, dataID).enqueue(object : Callback<GetDetailVideos> {
            override fun onResponse(
                call: Call<GetDetailVideos>,
                response: Response<GetDetailVideos>
            ) {
                resultsVideos.value =
                    ApiResponse.Success(response.body()?.results as List<ResultsVideos>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<GetDetailVideos>, t: Throwable) {
                Log.e(mTAG, "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }

        })
        return resultsVideos
    }

    fun getDetailTV(dataID: String): LiveData<ApiResponse<ResultsItem>> {
        EspressoIdlingResource.increment()
        val resultDetailItem = MutableLiveData<ApiResponse<ResultsItem>>()
        apiService.getDetailTV(dataID).enqueue(object : Callback<ResultsItem> {
            override fun onResponse(call: Call<ResultsItem>, response: Response<ResultsItem>) {
                resultDetailItem.value = ApiResponse.Success(response.body() as ResultsItem)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ResultsItem>, t: Throwable) {
                Log.d(mTAG, "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultDetailItem
    }

    fun getDetailMovie(dataID: String): LiveData<ApiResponse<ResultsItem>> {
        EspressoIdlingResource.increment()
        val resultDetailItem = MutableLiveData<ApiResponse<ResultsItem>>()
        apiService.getDetailMovie(dataID).enqueue(object : Callback<ResultsItem> {
            override fun onResponse(call: Call<ResultsItem>, response: Response<ResultsItem>) {
                resultDetailItem.value = ApiResponse.Success(response.body() as ResultsItem)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ResultsItem>, t: Throwable) {
                Log.d(mTAG, "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultDetailItem
    }

    fun getAllGenre(mediaType: String): LiveData<ApiResponse<List<ResultsGenre>>> {
        val resultsGenre = MutableLiveData<ApiResponse<List<ResultsGenre>>>()
        EspressoIdlingResource.increment()
        apiService.getGenre(mediaType).enqueue(object : Callback<DataGenre> {
            override fun onResponse(call: Call<DataGenre>, response: Response<DataGenre>) {
                resultsGenre.value =
                    ApiResponse.Success(response.body()?.genres as List<ResultsGenre>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DataGenre>, t: Throwable) {
                Log.e(mTAG, "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultsGenre
    }
}