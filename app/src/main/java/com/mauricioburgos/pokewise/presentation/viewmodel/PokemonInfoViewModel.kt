package com.mauricioburgos.pokewise.presentation.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mauricioburgos.pokewise.AppController
import com.mauricioburgos.pokewise.core.platform.Failure
import com.mauricioburgos.pokewise.data.repositories.PokemonsRepository
import com.mauricioburgos.pokewise.data.repositories.UserRepository
import com.mauricioburgos.pokewise.domain.ErrorResponse
import com.mauricioburgos.pokewise.domain.PokemonDetails
import com.mauricioburgos.pokewise.usecases.GetPokemonInfoUseCase
import com.mauricioburgos.pokewise.usecases.LoginUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


class PokemonInfoViewModel
@ViewModelInject
constructor(
  private val pokemonRepository: PokemonsRepository,
  private val getPokemonInfoUseCase: GetPokemonInfoUseCase,
  @Assisted private val savedStateHandle: SavedStateHandle

) : ViewModel()
{

  val error : MutableLiveData<ErrorResponse> = MutableLiveData<ErrorResponse>()
  private val allPokemon = MutableLiveData<PokemonDetails>()
  private val mPokemonItems = ArrayList<PokemonDetails>()
  private val allPokemonsSaved = MediatorLiveData<List<PokemonDetails>>()


  fun getMPokemon() = allPokemon

  fun getAllPokemons() = allPokemonsSaved


  init {
    viewModelScope.launch {
      getAllPokemonsSaved()
    }
  }



  suspend fun savePokemonDb(pokemonDetails: PokemonDetails){
    pokemonRepository.insertPokemon(pokemonDetails)
  }


  private suspend fun getAllPokemonsSaved(){
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