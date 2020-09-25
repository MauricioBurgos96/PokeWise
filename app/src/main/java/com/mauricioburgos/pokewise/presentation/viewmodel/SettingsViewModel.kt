package com.mauricioburgos.pokewise.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.mauricioburgos.pokewise.AppController
import com.mauricioburgos.pokewise.data.repositories.PokemonsRepository
import javax.inject.Inject


class SettingsViewModel() : ViewModel() {



  @Inject
  lateinit var pokemonRepository: PokemonsRepository


  init {
    AppController.component.inject(this)

  }



  suspend fun deleteAllSavedPokemons(){
    pokemonRepository.deleteSavedPokemons()
  }





}