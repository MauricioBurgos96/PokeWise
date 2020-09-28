package com.mauricioburgos.pokewise.core.di

import com.dacodes.censos.data.dao.PokemonDatabase
import com.mauricioburgos.pokewise.core.utils.NetworkHandler
import com.mauricioburgos.pokewise.core.utils.PreferencesHelper
import com.mauricioburgos.pokewise.data.implementations.PokemonsRepositoryImpl
import com.mauricioburgos.pokewise.data.implementations.UserRepositoryImpl
import com.mauricioburgos.pokewise.data.repositories.PokemonsRepository
import com.mauricioburgos.pokewise.data.repositories.UserRepository
import com.mauricioburgos.pokewise.domain.PokemonApi
import com.mauricioburgos.pokewise.domain.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun providesUserRepository(
        networkHandler: NetworkHandler,
        userApi: UserApi,
        preferencesHelper: PreferencesHelper
    ) : UserRepository = UserRepositoryImpl(
        networkHandler,
        userApi,
        preferencesHelper
    )

    @Singleton
    @Provides
    fun providesPokemonRepository(
        networkHandler: NetworkHandler,
        database: PokemonDatabase,
        pokemonApi: PokemonApi,
        preferencesHelper: PreferencesHelper
    ) : PokemonsRepository = PokemonsRepositoryImpl(
        networkHandler,
        database.pokemonDao(),
        pokemonApi,
        preferencesHelper

    )

    // @Provides
    // @Singleton
    // fun providesPokemonRepositoryImp(
    //     networkHandler: NetworkHandler,
    //     database: PokemonDatabase,
    //     apiProvider: ApiProvider,
    //     preferencesHelper: PreferencesHelper
    // ) : PokemonsRepositoryImpl = PokemonsRepositoryImpl(
    //     networkHandler,
    //     database.pokemonDao(),
    //     apiProvider.getEndpoint(PokemonApi::class.java),
    //     preferencesHelper

    // )

}