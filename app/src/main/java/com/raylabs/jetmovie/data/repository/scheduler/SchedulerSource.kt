package com.raylabs.jetmovie.data.repository.scheduler

interface SchedulerSource {

   suspend fun scheduleSuggestion()
}