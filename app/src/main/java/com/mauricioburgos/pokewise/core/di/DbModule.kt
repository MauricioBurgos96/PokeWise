package com.mauricioburgos.pokewise.core.di

import android.content.Context
import androidx.room.Room
import com.dacodes.censos.data.dao.PokemonDatabase
import com.mauricioburgos.pokewise.data.dao.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DbModule {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): PokemonDatabase {
        return Room
            .databaseBuilder(
                context,
                PokemonDatabase::class.java,
                PokemonDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(pokemonDatabase: PokemonDatabase): PokemonDao {
        return pokemonDatabase.pokemonDao()
    }
}