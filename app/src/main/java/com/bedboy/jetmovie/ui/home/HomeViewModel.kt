package com.bedboy.jetmovie.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bedboy.jetmovie.data.source.GenreRepository
import com.bedboy.jetmovie.data.source.local.entity.GenreEntity
import com.bedboy.jetmovie.data.source.remote.response.DataResponse
import com.bedboy.jetmovie.data.source.remote.response.ResultsItem
import com.bedboy.jetmovie.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val genreRepository: GenreRepository) : ViewModel() {

    private val dataTrending = MutableLiveData<List<ResultsItem>>()
    val trending: LiveData<List<ResultsItem>> = dataTrending

    private val dataPopular = MutableLiveData<List<ResultsItem>>()
    val popular: LiveData<List<ResultsItem>> = dataPopular

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "HomeViewModel"
        private val client = ApiConfig.getApiService()
        private var media_type: String = "movie"
    }

    init {
        getTrending()
        getPopular()
    }

    fun getGenre(): List<GenreEntity> = genreRepository.getGenre()

    private fun getPopular() {
        _isLoading.value = true
        client.getPopular().enqueue(object : Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    dataPopular.value = response.body()?.results
                    media_type = response.body()?.results!![0].mediaType
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    private fun getTrending() {
        _isLoading.value = true
        client.getTrending().enqueue(object : Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    dataTrending.value = response.body()?.results
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}