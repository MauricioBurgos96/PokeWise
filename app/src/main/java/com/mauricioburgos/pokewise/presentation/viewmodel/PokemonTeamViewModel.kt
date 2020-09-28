package com.mauricioburgos.pokewise.presentation.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mauricioburgos.pokewise.data.repositories.PokemonsRepository
import com.mauricioburgos.pokewise.domain.PokemonDetails
import kotlinx.coroutines.launch


class PokemonTeamViewModel
@ViewModelInject
constructor(
  private val pokemonRepository: PokemonsRepository,
  @Assisted private val savedStateHandle: SavedStateHandle

) : ViewModel() {

  private val allPokemonsSaved = MediatorLiveData<List<PokemonDetails>>()



  fun getAllPokemons() = allPokemonsSaved


  init {

    viewModelScope.launch {
      getAllPokemonsSaved()

    }

  }





  private suspend fun getAllPokemonsSaved(){
    allPokemonsSaved.addSource(pokemonRepository.getSavedPokemons()) { movies ->
      allPokemonsSaved.postValue(movies)
    }
  }

  suspend fun deletePokemonFromTeam(pokemonDetails : PokemonDetails){
    pokemonRepository.deletePokemonFromTeam(pokemonDetails)

  }








}