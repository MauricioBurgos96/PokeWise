package com.mauricioburgos.pokewise.presentation.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mauricioburgos.pokewise.data.datasource.PokemonDataSource
import com.mauricioburgos.pokewise.data.datasource.PokemonDataSourceFactory
import com.mauricioburgos.pokewise.domain.*


class PokemonsViewModel
@ViewModelInject
constructor(
    private val pokemonApi: PokemonApi,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var newsList: LiveData<PagedList<Pokemon>>
    private val pageSize = 50
    private val pokemonDataSourceFactory: PokemonDataSourceFactory

    init {

        pokemonDataSourceFactory =
            PokemonDataSourceFactory(pokemonApi)
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