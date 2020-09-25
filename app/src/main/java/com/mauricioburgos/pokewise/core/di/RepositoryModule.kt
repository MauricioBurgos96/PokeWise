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
import com.mauricioburgos.pokewise.framework.ApiProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providesUserRepository(
        networkHandler: NetworkHandler,
        apiProvider: ApiProvider,
        preferencesHelper: PreferencesHelper
    ) : UserRepository = UserRepositoryImpl(
        networkHandler,
        apiProvider.getEndpoint(UserApi::class.java),
        preferencesHelper
    )


    @Provides
    @Singleton
    fun providesPokemonRepository(
        networkHandler: NetworkHandler,
        database: PokemonDatabase,
        apiProvider: ApiProvider,
        preferencesHelper: PreferencesHelper
    ) : PokemonsRepository = PokemonsRepositoryImpl(
        networkHandler,
        database.pokemonDao(),
        apiProvider.getEndpoint(PokemonApi::class.java),
        preferencesHelper

    )

    @Provides
    @Singleton
    fun providesPokemonRepositoryImp(
        networkHandler: NetworkHandler,
        database: PokemonDatabase,
        apiProvider: ApiProvider,
        preferencesHelper: PreferencesHelper
    ) : PokemonsRepositoryImpl = PokemonsRepositoryImpl(
        networkHandler,
        database.pokemonDao(),
        apiProvider.getEndpoint(PokemonApi::class.java),
        preferencesHelper

    )


}