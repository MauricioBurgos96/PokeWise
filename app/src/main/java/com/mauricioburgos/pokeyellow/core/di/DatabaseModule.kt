package com.mauricioburgos.pokeyellow.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module

class DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(context: Context): PokemonDatabase = PokemonDatabase.getInstance(context)
}