package com.mauricioburgos.pokeyellow.core.di

import com.mauricioburgos.pokeyellow.AppController
import com.mauricioburgos.pokeyellow.presentation.view.home.pokemons.PokemonsFragment
import com.mauricioburgos.pokeyellow.presentation.view.home.profile.ProfileFragment
import com.mauricioburgos.pokeyellow.presentation.viewmodel.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        HelperModule::class,
        RepositoryModule::class,
        DatabaseModule::class
    ]
)

interface AppComponent {

    fun inject(target: AppController)
    fun inject(target: LoginViewModel)
    fun inject(target: PokemonsViewModel)
    fun inject(target: PokemonInfoViewModel)
    fun inject(target: PokemonTeamViewModel)
    fun inject(target: ProfileFragment)
    fun inject(target: SettingsViewModel)
    fun inject(target: PokemonsFragment)



}