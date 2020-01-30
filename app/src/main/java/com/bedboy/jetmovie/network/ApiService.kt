package com.bedboy.jetmovie.network

import com.bedboy.jetmovie.model.entity.DataDetail
import com.bedboy.jetmovie.model.entity.DataResult
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("?apikey=34278614")
    fun getResultsSearch(@Query("s") s: String?, @Query("page") page: Int?): Flowable<DataResult>

    @GET("?apikey=34278614")
    fun getDetailItem(@Query("i") i: String?): Flowable<DataDetail>
}
