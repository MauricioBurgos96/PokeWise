package com.mauricioburgos.pokeyellow.usecases

import com.mauricioburgos.pokeyellow.core.platform.Either
import com.mauricioburgos.pokeyellow.core.platform.Failure
import com.mauricioburgos.pokeyellow.core.platform.Paginable
import com.mauricioburgos.pokeyellow.core.platform.UseCase
import com.mauricioburgos.pokeyellow.data.repositories.PokemonsRepository
import com.mauricioburgos.pokeyellow.domain.GetPokemonsRequest
import com.mauricioburgos.pokeyellow.domain.Pokemon
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor (
    private val pokemonsRepository: PokemonsRepository
) : UseCase<List<Pokemon>, GetPokemonsRequest>(), Paginable {
    override var currentPage: Int = 0
    override var lastPage: Int = 1
    override var isLoading: Boolean = false
    override var isLastPage: Boolean = false
    override var hasNextPage: Boolean
        get() = !isLastPage
        set(value) {}


    override fun resetPager() {
        super.resetPager()
        cancelUseCase()
    }

    override suspend fun run(params: GetPokemonsRequest): Either<Failure, List<Pokemon>> {
        TODO("Not yet implemented")
    }

//    override suspend fun run(params: GetPokemonsRequest): Either<Failure, List<Pokemon>> {
//        if(isLoading){
//            return Either.Left(Failure.IsLoading)
//        }
//        else if(currentPage > lastPage){
//            isLastPage = true
//            return Either.Left(Failure.LastPage)
//        }
//        else{
//            isLoading = true
//            params.page = currentPage*20
//            currentPage++
//            val mEither = pokemonsRepository.getPokemons(params.toQueryMap())
//            var mFailure : Failure? = null
//            var mData : List<Pokemon>? = null
//
//            mEither.either(
//                {
//                    mFailure = it;
//                    it
//                },
//                {
//                    mData = it.data
//                    mData?.apply {
//                        lastPage = it.lastPage
//                        if (currentPage == lastPage) {
//                            isLastPage = true
//                        }
//                    }
//                }
//            )
//
//            isLoading = false
//            return if(mEither.isLeft){
//                Either.Left(mFailure!!)
//            } else{
//                Either.Right(mData?: emptyList())
//            }
//        }
//    }
}
