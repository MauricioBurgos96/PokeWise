package com.mauricioburgos.pokewise.usecases


import com.mauricioburgos.pokewise.core.platform.Either
import com.mauricioburgos.pokewise.core.platform.Failure
import com.mauricioburgos.pokewise.core.platform.UseCase
import com.mauricioburgos.pokewise.data.repositories.PokemonsRepository
import com.mauricioburgos.pokewise.domain.PokemonDetails
import javax.inject.Inject

class GetPokemonInfoUseCase @Inject constructor(
        private val pokemonRepository: PokemonsRepository
): UseCase<PokemonDetails, Int>() {
    override suspend fun run(params: Int): Either<Failure, PokemonDetails> {
        return pokemonRepository.getPokemonInfo(params)
    }
}