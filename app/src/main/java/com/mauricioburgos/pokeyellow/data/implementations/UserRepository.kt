package com.mauricioburgos.pokeyellow.data.implementations

import com.mauricioburgos.pokeyellow.core.platform.Either
import com.mauricioburgos.pokeyellow.core.platform.Failure
import com.mauricioburgos.pokeyellow.core.utils.Constants
import com.mauricioburgos.pokeyellow.data.repositories.UserRepository
import com.mauricioburgos.pokeyellow.core.utils.NetworkHandler
import com.mauricioburgos.pokeyellow.core.utils.PreferencesHelper
import com.mauricioburgos.pokeyellow.domain.ErrorResponse
import com.mauricioburgos.pokeyellow.domain.User
import com.mauricioburgos.pokeyellow.domain.UserApi
import com.mauricioburgos.pokeyellow.domain.UserSigninRequest
import com.mauricioburgos.pokeyellow.framework.ApiRequest


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