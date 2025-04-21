package com.raylabs.jetmovie.data.repository.scheduler

import android.util.Log
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.raylabs.jetmovie.data.source.remote.RemoteDataSource
import com.raylabs.jetmovie.data.source.scheduler.AppWorkers
import kotlinx.coroutines.flow.firstOrNull
import java.util.concurrent.TimeUnit

class SchedulerRepository private constructor(
    private val workManager: WorkManager,
    private val remoteDataSource: RemoteDataSource
) : SchedulerSource {

    companion object {
        @Volatile
        private var instance: SchedulerRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            workManager: WorkManager
        ): SchedulerRepository =
            instance ?: synchronized(this) {
                instance ?: SchedulerRepository(
                    remoteDataSource = remoteDataSource,
                    workManager = workManager
                )
            }
    }

    override suspend fun scheduleSuggestion() {
        val mData = remoteDataSource.fetchMovies(page = "4").firstOrNull()?.results?.firstOrNull()
        mData?.let {
            val workRequest = PeriodicWorkRequest.Builder(
                AppWorkers::class.java, 15, TimeUnit.MINUTES
            ).setInputData(
                workDataOf(
                    "title" to it.title,
                    "message" to it.overview,
                    "channel_id" to "default_channel"
                )
            ).build()
            workManager.enqueue(workRequest)
        }
        Log.d(SchedulerRepository::class.java.name, "scheduleSuggestion: $mData")
    }
}