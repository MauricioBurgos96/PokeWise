package com.mauricioburgos.pokewise.data.implementations

import com.mauricioburgos.pokewise.core.platform.Either
import com.mauricioburgos.pokewise.core.platform.Failure
import com.mauricioburgos.pokewise.core.utils.Constants
import com.mauricioburgos.pokewise.data.repositories.UserRepository
import com.mauricioburgos.pokewise.core.utils.NetworkHandler
import com.mauricioburgos.pokewise.core.utils.PreferencesHelper
import com.mauricioburgos.pokewise.domain.ErrorResponse
import com.mauricioburgos.pokewise.domain.UserApi
import com.mauricioburgos.pokewise.domain.UserSigninRequest
import com.mauricioburgos.pokewise.framework.ApiRequest


class UserRepositoryImpl(
    private val networkHandler: NetworkHandler,
    private val userApi: UserApi,
    private val preferencesHelper: PreferencesHelper
): UserRepository, ApiRequest {


    override fun authenticate(params: UserSigninRequest) : Either<Failure, Boolean> {
        return when(networkHandler.isConnected){
            true -> {
                preferencesHelper.putBoolean(Constants.PKEY_IS_LOGGED, true)
                Either.Right(true)
            }
            false, null -> Either.Left(Failure.ServerError(ErrorResponse(100,"No hay conexión a internet")))
        }

    }

    override fun getIsLogged(): Boolean {
        return preferencesHelper.getBoolean(Constants.PKEY_IS_LOGGED)
    }

    override fun clearLoggedUSer() {

    }


}