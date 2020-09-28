package com.mauricioburgos.pokewise.data.implementations

import androidx.lifecycle.LiveData
import com.mauricioburgos.pokewise.core.platform.Either
import com.mauricioburgos.pokewise.core.platform.Failure
import com.mauricioburgos.pokewise.core.utils.NetworkHandler
import com.mauricioburgos.pokewise.core.utils.PreferencesHelper
import com.mauricioburgos.pokewise.data.dao.PokemonDao
import com.mauricioburgos.pokewise.data.repositories.PokemonsRepository
import com.mauricioburgos.pokewise.domain.*
import com.mauricioburgos.pokewise.framework.ApiRequest


class PokemonsRepositoryImpl(
    private val networkHandler: NetworkHandler,
    private val pokemonDao: PokemonDao,
    private val pokemonApi: PokemonApi,
    private val preferencesHelper: PreferencesHelper
): PokemonsRepository, ApiRequest {


    override fun getPokemonInfo(id: Int): Either<Failure, PokemonDetails> {

        return when(networkHandler.isConnected){
            true -> request(pokemonApi.getPokemonInfo(id),
                {response-> response}, PokemonDetails.empty())
            false, null -> Either.Left(Failure.ServerError(
                ErrorResponse(100, "No hay conexión a internet")
            ))
        }
    }

    override suspend fun insertPokemon(pokemonDetails: PokemonDetails) {
        try {
            pokemonDao.insertPokemonTeam(pokemonDetails)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    override suspend fun getSavedPokemons() : LiveData<List<PokemonDetails>> {
       return pokemonDao.getAllPokemonTeam()
    }

    override suspend fun deleteSavedPokemons() {
        try {
            pokemonDao.deleteAllSavedPokemons()

        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

}