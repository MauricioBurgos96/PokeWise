package com.mauricioburgos.pokeyellow.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mauricioburgos.pokeyellow.AppController
import com.mauricioburgos.pokeyellow.core.platform.Failure
import com.mauricioburgos.pokeyellow.data.repositories.PokemonsRepository
import com.mauricioburgos.pokeyellow.domain.ErrorResponse
import com.mauricioburgos.pokeyellow.domain.PokemonDetails
import com.mauricioburgos.pokeyellow.usecases.GetPokemonInfoUseCase
import javax.inject.Inject


class PokemonInfoViewModel() : ViewModel() {

  val error : MutableLiveData<ErrorResponse> = MutableLiveData<ErrorResponse>()
  private val allPokemon = MutableLiveData<PokemonDetails>()
  private val mPokemonItems = ArrayList<PokemonDetails>()


  fun getMPokemon() = allPokemon

  @Inject
  lateinit var pokemonRepository: PokemonsRepository


  @Inject
  lateinit var getPokemonInfoUseCase: GetPokemonInfoUseCase


  init {
    AppController.component.inject(this)
  }



  fun savePokemonDb(pokemonDetails: PokemonDetails){
    pokemonRepository.insertCensoToSave(pokemonDetails)
  }


  fun loadCensosPokemon(censoId : Int) {

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
              { censos ->


                allPokemon.postValue(censos)



                censos
              }
      )
    }

  }






}