package com.mauricioburgos.pokeyellow.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mauricioburgos.pokeyellow.AppController
import com.mauricioburgos.pokeyellow.core.platform.Failure
import com.mauricioburgos.pokeyellow.data.repositories.PokemonsRepository
import com.mauricioburgos.pokeyellow.domain.ErrorResponse
import com.mauricioburgos.pokeyellow.domain.Pokemon
import com.mauricioburgos.pokeyellow.domain.PokemonDetails
import com.mauricioburgos.pokeyellow.usecases.GetPokemonInfoUseCase
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
    pokemonRepository.insertCensoToSave(pokemonDetails)
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
                  error.value = ErrorResponse(100,"Sin conexión a internet: modo offline")

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