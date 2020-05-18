package com.mauricioburgos.pokeyellow.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.mauricioburgos.pokeyellow.AppController
import com.mauricioburgos.pokeyellow.data.repositories.PokemonsRepository
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