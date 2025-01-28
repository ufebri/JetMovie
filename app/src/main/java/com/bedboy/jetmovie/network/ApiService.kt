package com.bedboy.jetmovie.network

import com.bedboy.jetmovie.BuildConfig
import com.bedboy.jetmovie.data.source.remote.response.DataGenre
import com.bedboy.jetmovie.data.source.remote.response.DataResponse
import com.bedboy.jetmovie.data.source.remote.response.GetDetailVideos
import com.bedboy.jetmovie.data.source.remote.response.ResultsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //Get Page 1 for Trending Section
    @GET("trending/all/day")
    fun getTrending(
        @Query("api_key") apiKey: String = BuildConfig.APIKEY,
        @Query("page") page: String = "1"
    ): Call<DataResponse>

    //Get Page 2 for Popular Section
    @GET("trending/all/day")
    fun getPopular(
        @Query("api_key") apiKey: String = BuildConfig.APIKEY,
        @Query("page") page: String = "1"
    ): Call<DataResponse>

    //Get Detail Videos
    @GET("{media_type}/{id}/videos")
    fun getDetailVideo(
        @Path("media_type") mediaType: String,
        @Path("id") id: String,
        @Query("api_key") apiKey: String = BuildConfig.APIKEY
    ): Call<GetDetailVideos>

    //Get Genre
    @GET("genre/{media_type}/list")
    fun getGenre(
        @Path("media_type") mediaType: String,
        @Query("api_key") apiKey: String = BuildConfig.APIKEY
    ): Call<DataGenre>

    //Get Detail Data
    @GET("tv/{id}")
    fun getDetailTV(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = BuildConfig.APIKEY
    ): Call<ResultsItem>

    @GET("movie/{id}")
    fun getDetailMovie(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = BuildConfig.APIKEY
    ): Call<ResultsItem>

    //Get Upcoming Movie
    @GET("movie/upcoming")
    fun getAllUpComingMovie(
        @Query("api_key") apiKey: String = BuildConfig.APIKEY,
        @Query("page") page: String = "1"
    ): Call<DataResponse>

    //Search Movie
    @GET("search/movie")
    fun getMovieByKeyword(
        @Query("api_key") apiKey: String = BuildConfig.APIKEY,
        @Query("query") keyword: String
    ): Call<DataResponse>
}