package com.lumi.moviecata.ui.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.lumi.moviecata.R
import com.lumi.moviecata.utils.DataDummy
import com.lumi.moviecata.utils.espresso.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummySeries = DataDummy.generateDummySeries()
    private lateinit var instrumentalContext: Context

    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setup(){
        instrumentalContext = InstrumentationRegistry.getInstrumentation().targetContext
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource())
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovies() {
        delayExtra()
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        delayExtra()
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_description)).check(matches(withText(dummyMovie[0].description)))
    }

    @Test
    fun loadSeries() {
        onView(withText("Series")).perform(click())
        onView(withId(R.id.rv_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_series)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummySeries.size))
    }

    @Test
    fun loadDetailSeries() {
        onView(withText("Series")).perform(click())
        delayExtra()
        onView(withId(R.id.rv_series)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        delayExtra()
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummySeries[0].title)))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_description)).check(matches(withText(dummySeries[0].description)))
    }

    private fun delayExtra() {
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}