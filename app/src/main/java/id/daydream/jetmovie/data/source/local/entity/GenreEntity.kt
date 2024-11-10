package id.daydream.jetmovie.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class GenreEntity(
    @PrimaryKey
    @NonNull
    var id: Int,
    var name: String
) : Parcelable
