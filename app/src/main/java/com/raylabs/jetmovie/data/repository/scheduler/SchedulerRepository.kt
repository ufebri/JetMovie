package com.raylabs.jetmovie.data.repository.scheduler

import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.raylabs.jetmovie.data.scheduler.ScheduleWorkers
import java.util.concurrent.TimeUnit

class SchedulerRepository private constructor(
    private val workManager: WorkManager
) : SchedulerSource {

    companion object {
        @Volatile
        private var instance: SchedulerRepository? = null

        fun getInstance(
            workManager: WorkManager
        ): SchedulerRepository =
            instance ?: synchronized(this) {
                instance ?: SchedulerRepository(
                    workManager = workManager
                )
            }
    }

    override suspend fun scheduleSuggestion() {
        val workRequest = PeriodicWorkRequest.Builder(
            ScheduleWorkers::class.java, 15, TimeUnit.MINUTES
        ).build()

        workManager.enqueueUniquePeriodicWork(
            "scheduleSuggestion",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }
}