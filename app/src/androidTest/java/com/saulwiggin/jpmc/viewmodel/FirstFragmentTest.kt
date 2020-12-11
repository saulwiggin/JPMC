package com.saulwiggin.jpmc.viewmodel

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.saulwiggin.jpmc.MainActivity
import com.saulwiggin.jpmc.R
import com.saulwiggin.jpmc.ui.main.AlbumsViewHolder
import com.saulwiggin.jpmc.utils.TestUtils.withRecyclerView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class FirstFragmentTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isRecyclerviewVisible(){
        Espresso.onView(ViewMatchers.withId(R.id.rvAlbums)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }

    @Test
    fun test_isLaunchListDispalyExpectedData() {
        val name = "omnis laborum odio"

        Espresso.onView(ViewMatchers.withId(R.id.rvAlbums)).perform(RecyclerViewActions.scrollToPosition<AlbumsViewHolder>(2))
        Espresso.onView(
            withRecyclerView(R.id.rvAlbums).atPositionOnView(
                2,R.id.txtJoke
            )
                ).check(ViewAssertions.matches(ViewMatchers.withText(name)))
    }

}