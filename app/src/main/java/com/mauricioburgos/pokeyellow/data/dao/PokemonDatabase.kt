package com.dacodes.censos.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mauricioburgos.pokeyellow.data.dao.PokemonDao
import com.mauricioburgos.pokeyellow.domain.PokemonDetails


@Database(
    entities = [
        PokemonDetails::class

    ], version = 1, exportSchema = false
)
@TypeConverters(com.mauricioburgos.pokeyellow.data.typeconverters.TypeConverters::class)
abstract class PokemonDatabase : RoomDatabase(){

    abstract fun pokemonDao(): PokemonDao


    companion object {

        private var INSTANCE: PokemonDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): PokemonDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE =
                        Room.databaseBuilder(context, PokemonDatabase::class.java, "pokemon.db")
                            .fallbackToDestructiveMigration()
                            .build()
                }
                return INSTANCE!!
            }
        }
    }
}