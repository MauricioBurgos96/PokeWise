package com.mauricioburgos.pokeyellow.presentation.view.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mauricioburgos.pokeyellow.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest{

    @get: Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun test_activity_in_view() {
        onView(withId(R.id.parent_container)).check(matches(isDisplayed()))

    }

    @Test
    fun test_toolbar_in_view() {
        onView(withId(R.id.homeToolbar)).check(matches(isDisplayed()))

    }

    @Test
    fun test_vibility_bottom_navigation() {
        onView(withId(R.id.bottomNavMain)).check(matches(isDisplayed()))
    }
}