package com.raylabs.jetmovie

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.raylabs.jetmovie.ui.DashboardActivity
import com.raylabs.jetmovie.utils.DataDummy
import com.raylabs.jetmovie.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyMovie = DataDummy.generateData()

    @get:Rule
    var activityRule = ActivityScenarioRule(DashboardActivity::class.java)


    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadFeatured() {
        onView(withId(R.id.rv_resultTrending)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_resultTrending)).perform(swipeLeft())
    }

    @Test
    fun loadPopular() {
        onView(withId(R.id.rv_resultsMovie)).apply {
            check(matches(isDisplayed()))
            perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        }
    }

    @Test
    fun loadDetailDataMovie() {
        onView(withId(R.id.rv_resultsMovie)).apply {
            check(matches(isDisplayed()))
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        }
        onView(withId(R.id.tv_titleFilm_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_categoryFilm_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_descriptionFilm_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ratingFilm_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.wv_youtube)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailDataTVShow() {
        onView(withId(R.id.rv_resultTrending)).apply {
            check(matches(isCompletelyDisplayed()))
            perform(click())
        }
        onView(withId(R.id.tv_titleFilm_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_categoryFilm_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_descriptionFilm_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ratingFilm_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.wv_youtube)).check(matches(isDisplayed()))
    }

    @Test
    fun addWatchList() {
        onView(withId(R.id.rv_resultTrending)).apply {
            check(matches(isCompletelyDisplayed()))
            perform(click())
        }
        onView(withId(R.id.action_watchlist)).check(matches(isDisplayed()))
        onView(withId(R.id.action_watchlist)).perform(click())
    }

    @Test
    fun loadWatchList() {
        onView(withId(R.id.navigation_watchlist)).perform(click())
        onView(withId(R.id.rv_watchList)).apply {
            check(matches(isCompletelyDisplayed()))
            perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
        }
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister((EspressoIdlingResource.idlingResource))
    }
}