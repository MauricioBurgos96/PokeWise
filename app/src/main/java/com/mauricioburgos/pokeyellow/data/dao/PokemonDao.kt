package com.mauricioburgos.pokeyellow.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mauricioburgos.pokeyellow.domain.PokemonDetails


@Dao
interface PokemonDao {


    @Query("SELECT * FROM team_pokemons")
    fun getAllPokemonTeam(): LiveData<List<PokemonDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonTeam(censoList: PokemonDetails)

    @Delete
    suspend fun deletePokemonTeam(censoList: PokemonDetails)


}