package com.raylabs.jetmovie.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey val keyOfContent: String,
    val nextKey: Int?,
    val lastUpdated: Long
)
