package com.mauricioburgos.pokeyellow.usecases


import com.mauricioburgos.pokeyellow.core.platform.Either
import com.mauricioburgos.pokeyellow.core.platform.Failure
import com.mauricioburgos.pokeyellow.core.platform.UseCase
import com.mauricioburgos.pokeyellow.data.repositories.PokemonsRepository
import com.mauricioburgos.pokeyellow.domain.PokemonDetails
import javax.inject.Inject

class GetPokemonInfoUseCase @Inject constructor(
        private val pokemonRepository: PokemonsRepository
): UseCase<PokemonDetails, Int>() {
    override suspend fun run(params: Int): Either<Failure, PokemonDetails> {
        return pokemonRepository.getPokemonInfo(params)
    }
}