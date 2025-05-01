package com.raylabs.jetmovie.data.scheduler

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.raylabs.jetmovie.domain.model.NotificationData
import com.raylabs.jetmovie.utils.Injection
import com.raylabs.jetmovie.utils.NotificationHelper
import kotlinx.coroutines.flow.firstOrNull

class ScheduleWorkers(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val notificationData =
            if (inputData.getString("channel_id") == "ReminderToWatch") {
                getFromWorkData()
            } else {
                val fetchedData = getFromFetch()

                // Check if the data fetched from the API is valid
                if (fetchedData.id.isEmpty() || fetchedData.title.isEmpty()) {
                    return Result.failure()  // Return failure if data is missing or invalid
                }
                fetchedData
            }

        if (ContextCompat.checkSelfPermission(
                applicationContext, Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationHelper.showNotification(applicationContext, notificationData)
        } else {
            return Result.failure()
        }
        return Result.success()
    }

    private suspend fun getFromFetch(): NotificationData {
        val remoteDataSource = Injection.remoteDataSource
        val mPage = (Math.random() * 100).toInt() + 1
        val mData =
            remoteDataSource.fetchMovies(page = "$mPage").firstOrNull()?.results?.firstOrNull()

        // Check if the fetched data is valid, return failure if data is missing
        if (mData == null) {
            return NotificationData(
                id = "", title = "No Data", description = "Failed to fetch data",
                backDropPath = null, posterPath = null, channelID = "ErrorChannel"
            )
        }


        return NotificationData(
            id = mData.id,
            title = mData.title ?: "",
            description = mData.overview,
            backDropPath = mData.backdropPath,
            posterPath = mData.posterPath,
            channelID = "DiscoverSuggestion"
        )
    }

    private fun getFromWorkData(): NotificationData = NotificationData(
        id = inputData.getString("id") ?: "",
        title = inputData.getString("title") ?: "",
        description = inputData.getString("message") ?: "",
        posterPath = inputData.getString("posterPath"),
        backDropPath = inputData.getString("backDropPath"),
        channelID = "ReminderToWatch"
    )
}