package com.mauricioburgos.pokewise.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.mauricioburgos.pokewise.AppController
import com.mauricioburgos.pokewise.data.repositories.PokemonsRepository
import com.mauricioburgos.pokewise.domain.PokemonDetails
import javax.inject.Inject


class PokemonTeamViewModel() : ViewModel() {

  private val allPokemonsSaved = MediatorLiveData<List<PokemonDetails>>()



  @Inject
  lateinit var pokemonRepository: PokemonsRepository


  fun getAllPokemons() = allPokemonsSaved


  init {
    AppController.component.inject(this)
    getAllPokemonsSaved()

  }





  private fun getAllPokemonsSaved(){
    allPokemonsSaved.addSource(pokemonRepository.getSavedPokemons()) { movies ->
      allPokemonsSaved.postValue(movies)
    }
  }








}