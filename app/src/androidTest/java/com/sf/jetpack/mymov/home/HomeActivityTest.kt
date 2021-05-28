package com.sf.jetpack.mymov.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.sf.jetpack.mymov.R
import com.sf.jetpack.mymov.utils.DummyData
import org.hamcrest.Matchers.not
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class HomeActivityTest {

    private val dummyMovie = DummyData.generateMovieListData()
    private val dummyTvShow = DummyData.generateTvShowListData()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun checkListMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun checkListTvShow() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun checkDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.textTvShowName)).check(matches(isDisplayed()))
        onView(withId(R.id.textTvShowName)).check(matches(withText(dummyMovie[0].name)))
        onView(withId(R.id.textDescription)).check(matches(withText(dummyMovie[0].description)))
        onView(withId(R.id.textPublishedYear)).check(matches(withText(dummyMovie[0].publishedYear)))
        onView(withId(R.id.textDirectorName)).check(matches(withText(dummyMovie[0].director)))
        onView(withId(R.id.webView)).check(matches(isDisplayed()))
        onView(withId(R.id.textLinkNotFound)).check(matches(not(isDisplayed())))
    }

    @Test
    fun checkMovieTrailerNotFound() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        onView(withId(R.id.textTvShowName)).check(matches(isDisplayed()))
        onView(withId(R.id.textTvShowName)).check(matches(withText(dummyMovie[3].name)))
        onView(withId(R.id.textDescription)).check(matches(withText(dummyMovie[3].description)))
        onView(withId(R.id.textPublishedYear)).check(matches(withText(dummyMovie[3].publishedYear)))
        onView(withId(R.id.textDirectorName)).check(matches(withText(dummyMovie[3].director)))
        onView(withId(R.id.webView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.textLinkNotFound)).check(matches(isDisplayed()))
    }

    @Test
    fun checkDetailTvShow() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.textTvShowName)).check(matches(isDisplayed()))
        onView(withId(R.id.textTvShowName)).check(matches(withText(dummyTvShow[0].name)))
        onView(withId(R.id.textDescription)).check(matches(withText(dummyTvShow[0].description)))
        onView(withId(R.id.textPublishedYear)).check(matches(withText(dummyTvShow[0].publishedYear)))
        onView(withId(R.id.textDirectorName)).check(matches(withText(dummyTvShow[0].creator)))
        onView(withId(R.id.webView)).check(matches(isDisplayed()))
        onView(withId(R.id.textLinkNotFound)).check(matches(not(isDisplayed())))
    }

    @Test
    fun checkTvShowTrailerNotFound() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))
        onView(withId(R.id.textTvShowName)).check(matches(isDisplayed()))
        onView(withId(R.id.textTvShowName)).check(matches(withText(dummyTvShow[4].name)))
        onView(withId(R.id.textDescription)).check(matches(withText(dummyTvShow[4].description)))
        onView(withId(R.id.textPublishedYear)).check(matches(withText(dummyTvShow[4].publishedYear)))
        onView(withId(R.id.textDirectorName)).check(matches(withText(dummyTvShow[4].creator)))
        onView(withId(R.id.webView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.textLinkNotFound)).check(matches(isDisplayed()))
    }
}