package com.raylabs.jetmovie.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.raylabs.jetmovie.data.NetworkBoundResource
import com.raylabs.jetmovie.data.scheduler.ScheduleWorkers
import com.raylabs.jetmovie.data.source.local.LocalDataSource
import com.raylabs.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.data.source.local.entity.GenreEntity
import com.raylabs.jetmovie.data.source.local.entity.VideoEntity
import com.raylabs.jetmovie.data.source.remote.ApiResponse
import com.raylabs.jetmovie.data.source.remote.RemoteDataSource
import com.raylabs.jetmovie.data.source.remote.response.ResultsGenre
import com.raylabs.jetmovie.data.source.remote.response.ResultsItem
import com.raylabs.jetmovie.data.source.remote.response.ResultsVideos
import com.raylabs.jetmovie.domain.model.NotificationData
import com.raylabs.jetmovie.utils.AppExecutors
import com.raylabs.jetmovie.utils.DataHelper
import com.raylabs.jetmovie.utils.DataMapper
import com.raylabs.jetmovie.vo.Resource
import java.util.concurrent.TimeUnit

class DataRepository private constructor(
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
    private val remoteDataSource: RemoteDataSource,
    private val workManager: WorkManager
) :
    DataSource {

    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors,
            workManager: WorkManager
        ): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(
                    localDataSource,
                    appExecutors,
                    remoteData,
                    workManager
                )
            }
    }

    override fun getTrending(): LiveData<Resource<PagedList<DataMovieTVEntity>>> {
        return object :
            NetworkBoundResource<PagedList<DataMovieTVEntity>, List<ResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<DataMovieTVEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getAllMovie(DataHelper.DataFrom.TRENDING.value),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<DataMovieTVEntity>?): Boolean =
                data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllTrending("2")

            override fun saveCallResult(data: List<ResultsItem>) {
                val listTrending =
                    DataMapper.toListEntities(data, DataHelper.DataFrom.TRENDING.value)
                localDataSource.insertTrending(listTrending)
            }
        }.asLiveData()
    }

    override fun getPopular(): LiveData<Resource<PagedList<DataMovieTVEntity>>> {
        return object :
            NetworkBoundResource<PagedList<DataMovieTVEntity>, List<ResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<DataMovieTVEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopular(), config).build()
            }


            override fun shouldFetch(data: PagedList<DataMovieTVEntity>?): Boolean =
                data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllTrending("1")

            override fun saveCallResult(data: List<ResultsItem>) {
                val listPopular =
                    DataMapper.toListEntities(data, DataHelper.DataFrom.TRENDING.value)
                localDataSource.insertPopular(listPopular)
            }
        }.asLiveData()

    }

    override fun getVideoDetail(
        mediaType: String,
        id: String
    ): LiveData<Resource<List<VideoEntity>>> {
        return object : NetworkBoundResource<List<VideoEntity>, List<ResultsVideos>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<VideoEntity>> =
                localDataSource.getVideo(id)

            override fun shouldFetch(data: List<VideoEntity>?): Boolean =
                data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsVideos>>> =
                remoteDataSource.getDetailVideos(mediaType, id)

            override fun saveCallResult(data: List<ResultsVideos>) {
                val listVideo = ArrayList<VideoEntity>()
                for (response in data) {
                    with(response) {
                        val video = VideoEntity(
                            id = id,
                            key = key
                        )
                        listVideo.add(video)
                    }
                }
                localDataSource.insertVideo(listVideo)
            }
        }.asLiveData()
    }

    override fun getGenre(mediaType: String): LiveData<Resource<List<GenreEntity>>> {
        return object : NetworkBoundResource<List<GenreEntity>, List<ResultsGenre>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<GenreEntity>> =
                localDataSource.getGenre()

            override fun shouldFetch(data: List<GenreEntity>?): Boolean =
                data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsGenre>>> =
                remoteDataSource.getAllGenre(mediaType)

            override fun saveCallResult(data: List<ResultsGenre>) {
                val listGenre = ArrayList<GenreEntity>()
                for (response in data) {
                    with(response) {
                        val genre = GenreEntity(
                            id = id,
                            name = name
                        )
                        listGenre.add(genre)
                        DataHelper.genres.add(genre)
                    }
                }
                localDataSource.insertGenre(listGenre)
            }
        }.asLiveData()
    }

    override fun getWatchList(): LiveData<PagedList<DataMovieTVEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()
        return LivePagedListBuilder(localDataSource.getWatchList(), config).build()
    }

    override fun setWatchList(data: DataMovieTVEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setWatchList(data, state)
        }
    }

    override fun getDetailTV(id: String): LiveData<Resource<DataMovieTVEntity>> {
        return object : NetworkBoundResource<DataMovieTVEntity, ResultsItem>(appExecutors) {
            override fun loadFromDB(): LiveData<DataMovieTVEntity> =
                localDataSource.getDetail(id)

            override fun shouldFetch(data: DataMovieTVEntity?): Boolean =
                data != null && data.overview == ""

            override fun createCall(): LiveData<ApiResponse<ResultsItem>> =
                remoteDataSource.getDetailTV(id)

            override fun saveCallResult(data: ResultsItem) {
                val listGenre = ArrayList<String>()
                for (i in data.genres?.indices!!) {
                    listGenre.add(data.genres[0].name)
                }

                val detailResult = DataMovieTVEntity(
                    id = data.id,
                    vote = data.voteAverage,
                    genre = listGenre.joinToString(),
                    overview = data.overview,
                    isFavorite = false,
                    backDropPath = data.backdropPath,
                    imagePath = data.posterPath,
                    title = data.title ?: data.name,
                    mediaType = data.mediaType,
                    dataFrom = "detailTV",
                    releaseData = data.firstAirDate
                )
                localDataSource.updateDetail(detailResult, false)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(id: String): LiveData<Resource<DataMovieTVEntity>> {
        return object : NetworkBoundResource<DataMovieTVEntity, ResultsItem>(appExecutors) {
            override fun loadFromDB(): LiveData<DataMovieTVEntity> =
                localDataSource.getDetail(id)

            override fun shouldFetch(data: DataMovieTVEntity?): Boolean =
                data != null && data.title == "" && data.overview == ""

            override fun createCall(): LiveData<ApiResponse<ResultsItem>> =
                remoteDataSource.getDetailMovie(id)

            override fun saveCallResult(data: ResultsItem) {
                val listGenre = ArrayList<String>()
                for (i in data.genres?.indices!!) {
                    listGenre.add(data.genres[0].name)
                }

                val detailResult = DataMovieTVEntity(
                    id = data.id,
                    title = data.title ?: data.name,
                    vote = data.voteAverage,
                    genre = listGenre.joinToString(),
                    overview = data.overview,
                    isFavorite = false,
                    backDropPath = data.backdropPath,
                    imagePath = data.posterPath,
                    mediaType = data.mediaType,
                    dataFrom = "detailMovie",
                    releaseData = data.releaseDate
                )
                localDataSource.updateDetail(detailResult, false)
            }
        }.asLiveData()
    }

    override fun getAllUpcoming(): LiveData<Resource<PagedList<DataMovieTVEntity>>> {
        return object :
            NetworkBoundResource<PagedList<DataMovieTVEntity>, List<ResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<DataMovieTVEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getAllMovie(DataHelper.DataFrom.UPCOMING.value),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<DataMovieTVEntity>?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllUpcoming()

            override fun saveCallResult(data: List<ResultsItem>) {
                val listTrending =
                    DataMapper.toListEntities(data, DataHelper.DataFrom.UPCOMING.value)
                localDataSource.insertTrending(listTrending)
            }
        }.asLiveData()
    }

    override fun getMovieByKeyword(keyword: String): LiveData<Resource<PagedList<DataMovieTVEntity>>> {
        return object :
            NetworkBoundResource<PagedList<DataMovieTVEntity>, List<ResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<DataMovieTVEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getMovieByKeyword(keyword),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<DataMovieTVEntity>?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getMovieByKeyword(keyword)

            override fun saveCallResult(data: List<ResultsItem>) {
                val listTrending = DataMapper.toListEntities(data, DataHelper.DataFrom.SEARCH.value)
                localDataSource.insertTrending(listTrending)
            }
        }.asLiveData()
    }

    override fun scheduleReminder(notificationData: NotificationData, triggerTimeMillis: Long) {
        val workRequest = OneTimeWorkRequestBuilder<ScheduleWorkers>()
            .setInputData(
                workDataOf(
                    "title" to notificationData.title,
                    "message" to notificationData.description,
                    "channel_id" to notificationData.channelID,
                    "id" to notificationData.id,
                    "posterPath" to notificationData.posterPath,
                    "backDropPath" to notificationData.backDropPath
                )
            )
            .setInitialDelay(triggerTimeMillis - System.currentTimeMillis(), TimeUnit.MILLISECONDS)
            .build()

        workManager.enqueueUniqueWork(
            "reminder_".plus(notificationData.title),
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }
}