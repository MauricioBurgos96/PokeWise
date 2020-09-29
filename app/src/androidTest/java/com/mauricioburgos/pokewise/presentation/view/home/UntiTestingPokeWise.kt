package com.mauricioburgos.pokewise.presentation.view.home

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dacodes.censos.data.dao.PokemonDatabase
import com.mauricioburgos.pokewise.data.dao.PokemonDao
import com.mauricioburgos.pokewise.domain.PokemonDetails
import com.mauricioburgos.pokewise.domain.UserSigninRequest
import junit.framework.Assert.assertEquals
import junit.framework.Assert.fail
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


@RunWith(AndroidJUnit4ClassRunner::class)
class UntiTestingPokeWise {

    var userSigninRequest =  UserSigninRequest("afsafsad@dsad.com","dasdsad")

    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertEquals(true,userSigninRequest.isEmailValid())
    }

    @Test
    fun passwordEmpty_CorrectPasswordSimple_ReturnsTrue() {
        assertEquals(true,userSigninRequest.isPasswordNotEmpty())
    }


    @get:Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var database: PokemonDatabase
    lateinit var pokemonDao: PokemonDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, PokemonDatabase::class.java).build()
        pokemonDao = database.pokemonDao()
    }


    @After
    @Throws(IOException::class)
    fun tearDown() {
        database.close()
    }

    @Test
    fun test_Insert_Pokemons_to_db_return_True() {
        val NUMBER_OF_POKEMONS = 5
        val pokemons = ArrayList<PokemonDetails>()

        pokemons.add(createFakePokemonData(1))
        pokemons.add(createFakePokemonData(2))
        pokemons.add(createFakePokemonData(3))
        pokemons.add(createFakePokemonData(4))
        pokemons.add(createFakePokemonData(5))
        runBlocking {
            pokemons.forEach {
                pokemonDao.insertPokemonTeam(it)
            }
        }

        val liveData = pokemonDao.getAllPokemonTeam()
        val pokemonsList = liveData.getValueBlocking()
        if (pokemonsList != null) {
            assertEquals(pokemonsList.size, NUMBER_OF_POKEMONS)
            Log.d("pokemon_test", pokemonsList.size.toString())
        } else {
            fail()
        }
    }

    @Test
    fun test_delete_all_Pokemons_to_db_return_True() {
        val pokemons = ArrayList<PokemonDetails>()

        pokemons.add(createFakePokemonData(1))
        pokemons.add(createFakePokemonData(2))


        runBlocking {
            pokemons.forEach {
                pokemonDao.insertPokemonTeam(it)
            }

            pokemonDao.deleteAllSavedPokemons()
        }

        val liveData = pokemonDao.getAllPokemonTeam()
        val pokemonsList = liveData.getValueBlocking()
        if (pokemonsList != null) {
            assertEquals(pokemonsList.size, 0)
            Log.d("pokemon_test", pokemonsList.size.toString())
        } else {
            fail()
        }
    }


    private fun createFakePokemonData(id: Int): PokemonDetails {
        //method omitted for brevity
        return  PokemonDetails(id,null,5f,"sd",0,0)
    }


    @Throws(InterruptedException::class)
    fun <T> LiveData<T>.getValueBlocking(): T? {
        var value: T? = null
        val latch = CountDownLatch(1)
        val innerObserver = Observer<T> {
            value = it
            latch.countDown()
        }
        observeForever(innerObserver)
        latch.await(2, TimeUnit.SECONDS)
        return value
    }

}

