package com.mauricioburgos.pokeyellow.data.repositories

import androidx.lifecycle.LiveData
import com.mauricioburgos.pokeyellow.core.platform.Either
import com.mauricioburgos.pokeyellow.core.platform.Failure
import com.mauricioburgos.pokeyellow.domain.GetPokemonsRequest
import com.mauricioburgos.pokeyellow.domain.Pokemon
import com.mauricioburgos.pokeyellow.domain.PokemonDetails
import com.mauricioburgos.pokeyellow.framework.ApiResponse
import okhttp3.ResponseBody


interface PokemonsRepository {

    fun getPokemonInfo(id: Int) : Either<Failure, PokemonDetails>
    suspend fun insertCensoToSave(pokemonDetails: PokemonDetails)


}