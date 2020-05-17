package com.mauricioburgos.pokeyellow.data.repositories

import com.mauricioburgos.pokeyellow.core.platform.Either
import com.mauricioburgos.pokeyellow.core.platform.Failure
import com.mauricioburgos.pokeyellow.domain.UserSigninRequest


interface UserRepository {
    fun clearLoggedUSer()
    fun getIsLogged(): Boolean
    fun authenticate(params: UserSigninRequest) : Either<Failure, Boolean>

}