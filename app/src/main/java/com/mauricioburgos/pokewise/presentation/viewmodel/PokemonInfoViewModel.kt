package com.mauricioburgos.pokewise.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mauricioburgos.pokewise.AppController
import com.mauricioburgos.pokewise.core.platform.Failure
import com.mauricioburgos.pokewise.data.repositories.PokemonsRepository
import com.mauricioburgos.pokewise.domain.ErrorResponse
import com.mauricioburgos.pokewise.domain.PokemonDetails
import com.mauricioburgos.pokewise.usecases.GetPokemonInfoUseCase
import javax.inject.Inject


class PokemonInfoViewModel() : ViewModel() {

  val error : MutableLiveData<ErrorResponse> = MutableLiveData<ErrorResponse>()
  private val allPokemon = MutableLiveData<PokemonDetails>()
  private val mPokemonItems = ArrayList<PokemonDetails>()
  private val allPokemonsSaved = MediatorLiveData<List<PokemonDetails>>()


  fun getMPokemon() = allPokemon

  @Inject
  lateinit var pokemonRepository: PokemonsRepository




  @Inject
  lateinit var getPokemonInfoUseCase: GetPokemonInfoUseCase
  fun getAllPokemons() = allPokemonsSaved


  init {
    AppController.component.inject(this)
    getAllPokemonsSaved()


  }



  suspend fun savePokemonDb(pokemonDetails: PokemonDetails){
    pokemonRepository.insertPokemon(pokemonDetails)
  }


  private fun getAllPokemonsSaved(){
    allPokemonsSaved.addSource(pokemonRepository.getSavedPokemons()) { movies ->
      allPokemonsSaved.postValue(movies)
    }
  }

  fun loadPokemon(censoId : Int) {

    getPokemonInfoUseCase.invoke(censoId)
    {
      it.either(
              { failure ->
                val errorResponse = (failure as Failure.ServerError).errorResponse
                if(errorResponse.code==100){
                  error.value = ErrorResponse(100,"Sin conexión a internet")

                }
                else{
                  error.value= (failure as Failure.ServerError).errorResponse
                }
                failure
              },
              { pokemon ->

                allPokemon.postValue(pokemon)

                pokemon
              }
      )
    }

  }






}