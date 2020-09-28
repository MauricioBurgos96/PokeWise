package com.mauricioburgos.pokewise.data.repositories

import androidx.lifecycle.LiveData
import com.mauricioburgos.pokewise.core.platform.Either
import com.mauricioburgos.pokewise.core.platform.Failure
import com.mauricioburgos.pokewise.domain.PokemonDetails


interface PokemonsRepository {

    fun getPokemonInfo(id: Int) : Either<Failure, PokemonDetails>
    suspend fun insertPokemon(pokemonDetails: PokemonDetails)
    suspend fun getSavedPokemons() : LiveData<List<PokemonDetails>>
    suspend fun deleteSavedPokemons()


}