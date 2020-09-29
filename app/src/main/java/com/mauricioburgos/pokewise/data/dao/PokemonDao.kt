package com.mauricioburgos.pokewise.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dacodes.censos.data.dao.BaseDao
import com.mauricioburgos.pokewise.domain.PokemonDetails


@Dao
interface PokemonDao {

    @Query("SELECT * FROM team_pokemons")
    fun getAllPokemonTeam(): LiveData<List<PokemonDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonTeam(censoList: PokemonDetails)

    @Delete
    suspend fun deletePokemonTeam(censoList: PokemonDetails)

    @Query("DELETE FROM team_pokemons")
    suspend fun deleteAllSavedPokemons()

    @Delete
    fun delete(pokemonDetails: PokemonDetails)
}