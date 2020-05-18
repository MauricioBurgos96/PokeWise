package com.mauricioburgos.pokeyellow.data.implementations

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


class PokemonsRepositoryImpl(
    private val networkHandler: NetworkHandler,
    private val pokemonApi: PokemonApi,
    private val preferencesHelper: PreferencesHelper
): PokemonsRepository, ApiRequest {





}