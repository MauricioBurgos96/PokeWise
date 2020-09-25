package com.mauricioburgos.pokewise.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.mauricioburgos.pokewise.domain.Pokemon
import com.mauricioburgos.pokewise.domain.PokemonApi
import com.mauricioburgos.pokewise.domain.State
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class PokemonDataSource(
    private val pokemonApi: PokemonApi
    )
    : PageKeyedDataSource<Int, Pokemon>() {


    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    private val compositeDisposable = CompositeDisposable()
    var error: MutableLiveData<String> = MutableLiveData()



    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Pokemon>) {
            updateState(State.LOADING)
            compositeDisposable.add(pokemonApi.getPokemons((0), params.requestedLoadSize)
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        callback.onResult(
                            response.results,
                            null,
                            50
                        )
                    },
                    {
                        updateState(State.ERROR)
                        setRetry(Action { loadInitial(params, callback) })


                    }
                )
            )



    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Pokemon>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            pokemonApi.getPokemons(params.key, params.requestedLoadSize)
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        callback.onResult(response.results,
                            params.key + 50
                        )
                    },
                    {

                        updateState(State.ERROR)
                        setRetry(Action { loadAfter(params, callback) })
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Pokemon>) {
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    private fun setError(state: String) {
        this.error.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
        }
    }

    fun dispose(){
        compositeDisposable.dispose()
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

}