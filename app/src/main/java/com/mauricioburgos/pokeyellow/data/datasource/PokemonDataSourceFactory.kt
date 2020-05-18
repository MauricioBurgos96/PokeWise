package com.mauricioburgos.pokeyellow.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mauricioburgos.pokeyellow.domain.Pokemon
import com.mauricioburgos.pokeyellow.domain.PokemonApi
import io.reactivex.disposables.CompositeDisposable

class PokemonDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val pokemonApi: PokemonApi)
    : DataSource.Factory<Int, Pokemon>() {

    val newsDataSourceLiveData = MutableLiveData<PokemonDataSource>()

    override fun create(): DataSource<Int, Pokemon> {
        val newsDataSource = PokemonDataSource(pokemonApi, compositeDisposable)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}