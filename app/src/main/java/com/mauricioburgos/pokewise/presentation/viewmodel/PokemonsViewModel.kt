package com.mauricioburgos.pokewise.presentation.viewmodel

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mauricioburgos.pokewise.AppController
import com.mauricioburgos.pokewise.data.datasource.PokemonDataSource
import com.mauricioburgos.pokewise.data.datasource.PokemonDataSourceFactory
import com.mauricioburgos.pokewise.domain.*
import com.mauricioburgos.pokewise.framework.ApiProvider
import javax.inject.Inject


class PokemonsViewModel() : ViewModel() {

    var newsList: LiveData<PagedList<Pokemon>>
    private val pageSize = 50
    private val pokemonDataSourceFactory: PokemonDataSourceFactory

    @Inject
    lateinit var apiProvider: ApiProvider


    init {
        AppController.component.inject(this)

        pokemonDataSourceFactory = PokemonDataSourceFactory(apiProvider.getEndpoint(PokemonApi::class.java))
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize)
            .setEnablePlaceholders(false)
            .build()
        newsList = LivePagedListBuilder<Int, Pokemon>(pokemonDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap<PokemonDataSource,
            State>(pokemonDataSourceFactory.newsDataSourceLiveData, PokemonDataSource::state)


    fun getError(): LiveData<String> = Transformations.switchMap<PokemonDataSource,
            String>(pokemonDataSourceFactory.newsDataSourceLiveData, PokemonDataSource::error)

    fun retry() {
        pokemonDataSourceFactory.newsDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return newsList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        pokemonDataSourceFactory.newsDataSourceLiveData.value?.dispose()
    }

}