package com.raylabs.jetmovie.domain.model

data class NotificationData(
    val id: String,
    val title: String,
    val description: String,
    val posterPath: String? = "",
    val backDropPath: String? = "",
    val channelID: String
)