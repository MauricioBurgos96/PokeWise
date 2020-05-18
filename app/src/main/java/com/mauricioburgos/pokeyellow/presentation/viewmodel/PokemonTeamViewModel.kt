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