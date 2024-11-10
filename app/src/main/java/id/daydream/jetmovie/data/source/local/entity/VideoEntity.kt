package id.daydream.jetmovie.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VideoEntity(
    @PrimaryKey
    @NonNull
    var id: String,

    var key: String
)
