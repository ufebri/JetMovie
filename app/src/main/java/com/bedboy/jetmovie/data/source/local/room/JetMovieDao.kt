package com.bedboy.jetmovie.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.data.source.local.entity.GenreEntity
import com.bedboy.jetmovie.data.source.local.entity.PopularEntity
import com.bedboy.jetmovie.data.source.local.entity.VideoEntity

@Dao
interface JetMovieDao {

    //Trending Operations
    @Query("SELECT * FROM dataMovieTVEntities")
    fun getTrending(): DataSource.Factory<Int, DataMovieTVEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrending(trending: List<DataMovieTVEntity>)

    //Popular Operations
    @Query("SELECT * FROM popularEntities")
    fun getPopular(): DataSource.Factory<Int, PopularEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopular(popular: List<PopularEntity>)

    //Genre Operations
    @Query("SELECT * FROM GenreEntity")
    fun getGenre(): LiveData<List<GenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenre(genre: List<GenreEntity>)

    //Video Operations
    @Query("SELECT * FROM VideoEntity WHERE id = :id")
    fun getVideo(id: String): LiveData<List<VideoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideo(video: List<VideoEntity>)

    //Detail Operations
    @Query("SELECT * FROM dataMovieTVEntities WHERE id = :id")
    fun getDetailByID(id: String): LiveData<DataMovieTVEntity>

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateDetailByID(detail: DataMovieTVEntity)

    //WatchList Operations
    @Query("SELECT * FROM dataMovieTVEntities WHERE isFavorite = 1")
    fun getWatchList(): DataSource.Factory<Int, DataMovieTVEntity>

}