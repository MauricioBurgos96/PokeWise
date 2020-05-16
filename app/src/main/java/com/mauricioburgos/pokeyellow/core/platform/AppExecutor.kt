package com.mauricioburgos.pokeyellow.core.platform
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppExecutor @Inject constructor(
    var dbThread: Executor,
    var analyzerThread: Executor,
    var detectorThread: Executor
) {
    fun dbThread(): Executor {
        return dbThread
    }

    fun analyzerThread(): Executor {
        return analyzerThread
    }

    fun detectorThread(): Executor {
        return detectorThread
    }

    companion object {
        val TAG = AppExecutor::class.java.simpleName
    }

}