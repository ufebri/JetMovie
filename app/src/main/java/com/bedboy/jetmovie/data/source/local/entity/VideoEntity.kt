package com.bedboy.jetmovie.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoEntity(
    var key: String
) : Parcelable
