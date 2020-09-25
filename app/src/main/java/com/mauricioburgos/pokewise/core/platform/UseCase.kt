package com.mauricioburgos.pokewise.core.platform

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default

abstract class UseCase<out Type, in Params> where Type : Any {
    abstract suspend fun run(params: Params): Either<Failure, Type>

    var theJob: Job? = null
    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) =
        CoroutineScope(Default).launch {
            val job = async { run(params) }
            theJob = job
            launch(Dispatchers.Main) {
                onResult(job.await())
                theJob = null
            }
        }

    fun cancelUseCase(){
        theJob?.cancel()
    }

    class None
}