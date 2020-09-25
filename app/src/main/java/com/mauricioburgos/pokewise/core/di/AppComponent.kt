package com.mauricioburgos.pokewise.core.di

import com.mauricioburgos.pokewise.AppController
import com.mauricioburgos.pokewise.data.datasource.PokemonDataSourceFactory
import com.mauricioburgos.pokewise.presentation.view.home.pokemons.PokemonsFragment
import com.mauricioburgos.pokewise.presentation.view.home.profile.ProfileFragment
import com.mauricioburgos.pokewise.presentation.viewmodel.*
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
    fun inject(target: PokemonDataSourceFactory)



}