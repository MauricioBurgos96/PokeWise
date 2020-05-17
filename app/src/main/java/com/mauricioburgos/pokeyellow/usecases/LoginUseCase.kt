package com.mauricioburgos.pokeyellow.usecases


import com.mauricioburgos.pokeyellow.core.platform.Either
import com.mauricioburgos.pokeyellow.core.platform.Failure
import com.mauricioburgos.pokeyellow.core.platform.UseCase
import com.mauricioburgos.pokeyellow.data.repositories.UserRepository
import com.mauricioburgos.pokeyellow.domain.User
import com.mauricioburgos.pokeyellow.domain.UserSigninRequest
import javax.inject.Inject


class  LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Boolean, UserSigninRequest>()
{
    override suspend fun run(params: UserSigninRequest): Either<Failure, Boolean> {

        return userRepository.authenticate(params)
    }
}