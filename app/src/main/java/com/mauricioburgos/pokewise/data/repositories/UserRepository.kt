package com.mauricioburgos.pokewise.data.repositories

import com.mauricioburgos.pokewise.core.platform.Either
import com.mauricioburgos.pokewise.core.platform.Failure
import com.mauricioburgos.pokewise.domain.UserSigninRequest


interface UserRepository {
    fun clearLoggedUSer()
    fun getIsLogged(): Boolean
    fun authenticate(params: UserSigninRequest) : Either<Failure, Boolean>

}