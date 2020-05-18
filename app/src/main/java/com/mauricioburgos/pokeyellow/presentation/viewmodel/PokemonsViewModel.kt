package com.mauricioburgos.pokeyellow.presentation.viewmodel

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mauricioburgos.pokeyellow.AppController
import com.mauricioburgos.pokeyellow.core.platform.Failure
import com.mauricioburgos.pokeyellow.data.datasource.PokemonDataSource
import com.mauricioburgos.pokeyellow.data.datasource.PokemonDataSourceFactory
import com.mauricioburgos.pokeyellow.data.repositories.UserRepository
import com.mauricioburgos.pokeyellow.domain.*
import com.mauricioburgos.pokeyellow.framework.ApiProvider
import com.mauricioburgos.pokeyellow.usecases.GetPokemonsUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class PokemonsViewModel() : ViewModel() {

    var newsList: LiveData<PagedList<Pokemon>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 50
    private val pokemonDataSourceFactory: PokemonDataSourceFactory

    @Inject
    lateinit var apiProvider: ApiProvider

    init {
        AppController.component.inject(this)

        pokemonDataSourceFactory = PokemonDataSourceFactory(compositeDisposable, apiProvider.getEndpoint(PokemonApi::class.java))
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize)
            .setEnablePlaceholders(false)
            .build()
        newsList = LivePagedListBuilder<Int, Pokemon>(pokemonDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap<PokemonDataSource,
            State>(pokemonDataSourceFactory.newsDataSourceLiveData, PokemonDataSource::state)

    fun retry() {
        pokemonDataSourceFactory.newsDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return newsList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }







}