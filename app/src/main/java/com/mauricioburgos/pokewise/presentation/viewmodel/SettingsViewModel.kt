package com.mauricioburgos.pokewise.presentation.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mauricioburgos.pokewise.AppController
import com.mauricioburgos.pokewise.data.repositories.PokemonsRepository
import javax.inject.Inject


class SettingsViewModel
@ViewModelInject
constructor(
  private val pokemonRepository: PokemonsRepository,
  @Assisted private val savedStateHandle: SavedStateHandle

) : ViewModel() {



  suspend fun deleteAllSavedPokemons(){
    pokemonRepository.deleteSavedPokemons()
  }





}