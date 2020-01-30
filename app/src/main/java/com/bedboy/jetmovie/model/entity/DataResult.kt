package com.bedboy.jetmovie.model.entity

import com.google.gson.annotations.SerializedName

data class DataResult(

    @SerializedName("Search") val searches: List<Results>,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("Response") val response: Boolean
)