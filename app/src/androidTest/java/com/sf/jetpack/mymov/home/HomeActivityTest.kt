package com.sf.jetpack.mymov.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.sf.jetpack.mymov.R
import com.sf.jetpack.mymov.utils.DummyData
import com.sf.jetpack.mymov.utils.EspressoIdleResource
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.junit.Rule

class HomeActivityTest {

    private val dummyMovie = DummyData.generateListMovieResponse().results

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdleResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdleResource.idlingResource)
    }

    @Test
    fun checkListMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun checkListTvShow() {
        onView(withId(R.id.navigation_tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun checkDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.imageMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.imageMovieCover)).check(matches(isDisplayed()))
        onView(withId(R.id.textMovieName)).check(matches(isDisplayed()))
        onView(withId(R.id.textOverview)).check(matches(isDisplayed()))
    }

    @Test
    fun checkDetailTvShow() {
        onView(withId(R.id.navigation_tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.imageTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.imageTvShowCover)).check(matches(isDisplayed()))
        onView(withId(R.id.textTvShowName)).check(matches(isDisplayed()))
        onView(withId(R.id.textOverview)).check(matches(isDisplayed()))
    }

    @Test
    fun loadBookmarkMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.imageBookmark)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.navigation_bookmark)).perform(click())
        onView(withId(R.id.rv_movie_bookmark)).check(matches(isDisplayed()))
        onView(isRoot()).perform(pressBack())
    }

    @Test
    fun loadBookmarkTvShow() {
        onView(withId(R.id.navigation_tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.imageBookmark)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.navigation_bookmark)).perform(click())
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_shows_bookmark)).check(matches(isDisplayed()))
        onView(isRoot()).perform(pressBack())
    }
}