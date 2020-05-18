package com.mauricioburgos.pokeyellow.data.implementations

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.mauricioburgos.pokeyellow.core.platform.Either
import com.mauricioburgos.pokeyellow.core.platform.Failure
import com.mauricioburgos.pokeyellow.core.utils.Constants
import com.mauricioburgos.pokeyellow.data.repositories.UserRepository
import com.mauricioburgos.pokeyellow.core.utils.NetworkHandler
import com.mauricioburgos.pokeyellow.core.utils.PreferencesHelper
import com.mauricioburgos.pokeyellow.data.repositories.PokemonsRepository
import com.mauricioburgos.pokeyellow.domain.*
import com.mauricioburgos.pokeyellow.framework.ApiRequest
import com.mauricioburgos.pokeyellow.framework.ApiResponse
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody


class PokemonsRepositoryImpl(
    private val networkHandler: NetworkHandler,
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




}