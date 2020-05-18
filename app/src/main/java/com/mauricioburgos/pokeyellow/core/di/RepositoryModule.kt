package com.mauricioburgos.pokeyellow.core.di


import com.mauricioburgos.pokeyellow.core.utils.NetworkHandler
import com.mauricioburgos.pokeyellow.core.utils.PreferencesHelper
import com.mauricioburgos.pokeyellow.data.implementations.PokemonsRepositoryImpl
import com.mauricioburgos.pokeyellow.data.implementations.UserRepositoryImpl
import com.mauricioburgos.pokeyellow.data.repositories.PokemonsRepository
import com.mauricioburgos.pokeyellow.data.repositories.UserRepository
import com.mauricioburgos.pokeyellow.domain.PokemonApi
import com.mauricioburgos.pokeyellow.domain.UserApi
import com.mauricioburgos.pokeyellow.framework.ApiProvider
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
        apiProvider: ApiProvider,
        preferencesHelper: PreferencesHelper
    ) : PokemonsRepository = PokemonsRepositoryImpl(
        networkHandler,
        apiProvider.getEndpoint(PokemonApi::class.java),
        preferencesHelper

    )


}