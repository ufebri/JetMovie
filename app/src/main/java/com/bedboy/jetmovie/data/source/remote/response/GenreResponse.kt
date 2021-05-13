package com.bedboy.jetmovie.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreResponse(
    var id: Int,
    var name: String
) : Parcelable