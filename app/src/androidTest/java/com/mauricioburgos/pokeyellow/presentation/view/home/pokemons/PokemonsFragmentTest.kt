package com.mauricioburgos.pokeyellow.presentation.view.home.pokemons

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mauricioburgos.pokeyellow.R
import com.mauricioburgos.pokeyellow.presentation.view.adapters.PokemonAdapter
import com.mauricioburgos.pokeyellow.presentation.view.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class PokemonsFragmentTest{

    @get: Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun is_reciclerview_show() {
        onView(ViewMatchers.withId(R.id.pokemonRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_selectedListItem_isDetailShow() {
        onView(ViewMatchers.withId(R.id.pokemonRecyclerView)).perform(actionOnItemAtPosition<PokemonAdapter.PokemonHolder>(0,click()))
        onView(withId(R.id.tvPokemonTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_back_to_pokemonFragment() {
        onView(ViewMatchers.withId(R.id.pokemonRecyclerView)).perform(actionOnItemAtPosition<PokemonAdapter.PokemonHolder>(0,click()))
        onView(withId(R.id.tvPokemonTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.pokemonRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}