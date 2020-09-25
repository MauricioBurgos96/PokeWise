package com.mauricioburgos.pokewise.usecases


import com.mauricioburgos.pokewise.core.platform.Either
import com.mauricioburgos.pokewise.core.platform.Failure
import com.mauricioburgos.pokewise.core.platform.UseCase
import com.mauricioburgos.pokewise.data.repositories.UserRepository
import com.mauricioburgos.pokewise.domain.UserSigninRequest
import javax.inject.Inject


class  LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Boolean, UserSigninRequest>()
{
    override suspend fun run(params: UserSigninRequest): Either<Failure, Boolean> {

        return userRepository.authenticate(params)
    }
}