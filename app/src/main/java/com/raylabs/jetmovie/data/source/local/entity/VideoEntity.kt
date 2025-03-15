package com.raylabs.jetmovie.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VideoEntity(
    @PrimaryKey
    var id: String,

    var key: String
)
