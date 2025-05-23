package com.raylabs.jetmovie.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.raylabs.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.data.source.local.entity.GenreEntity
import com.raylabs.jetmovie.data.source.local.entity.VideoEntity

@Dao
interface JetMovieDao {

    //Trending Operations
    @Query("SELECT  * FROM dataMovieTVEntities WHERE dataFrom = :dataFrom")
    fun getAllMovie(dataFrom: String): DataSource.Factory<Int, DataMovieTVEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTrending(trending: List<DataMovieTVEntity>)

    //Popular Operations
    @Query("SELECT * FROM dataMovieTVEntities ORDER BY vote DESC")
    fun getPopular(): DataSource.Factory<Int, DataMovieTVEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopular(popular: List<DataMovieTVEntity>)

    //Genre Operations
    @Query("SELECT * FROM GenreEntity")
    fun getGenre(): LiveData<List<GenreEntity>>

    @Query("SELECT * FROM GENREENTITY")
    fun getGenres(): List<GenreEntity>

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

    @Update
    fun updateDetailByID(detail: DataMovieTVEntity)

    //WatchList Operations
    @Query("SELECT * FROM dataMovieTVEntities WHERE isFavorite = 1")
    fun getWatchList(): DataSource.Factory<Int, DataMovieTVEntity>

    @Query("SELECT * FROM dataMovieTVEntities WHERE title = :keyword")
    fun getMovieByKeyword(keyword: String): DataSource.Factory<Int, DataMovieTVEntity>
}