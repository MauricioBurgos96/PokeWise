package com.mauricioburgos.pokewise.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mauricioburgos.pokewise.domain.Pokemon
import com.mauricioburgos.pokewise.domain.PokemonApi

class PokemonDataSourceFactory(
    private val pokemonApi: PokemonApi
)
    : DataSource.Factory<Int, Pokemon>() {

    val newsDataSourceLiveData = MutableLiveData<PokemonDataSource>()


    override fun create(): DataSource<Int, Pokemon> {
        val newsDataSource = PokemonDataSource(pokemonApi)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}