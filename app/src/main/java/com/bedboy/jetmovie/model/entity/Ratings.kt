package com.bedboy.jetmovie.model.entity

import com.google.gson.annotations.SerializedName

data class Ratings(
    @SerializedName("Source") val source: String,
    @SerializedName("Value") val value: String
)
