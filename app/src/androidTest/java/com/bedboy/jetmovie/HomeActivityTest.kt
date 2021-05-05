package com.bedboy.jetmovie

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.bedboy.jetmovie.ui.home.HomeActivity
import com.bedboy.jetmovie.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyMovie = DataDummy.generateMovie()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadFeatured() {
        onView(withId(R.id.vp_home)).check(matches(isDisplayed()))
        onView(withId(R.id.vp_home)).perform(swipeLeft())
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
    fun loadDetailData() {
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
}