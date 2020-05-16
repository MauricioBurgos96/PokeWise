package com.mauricioburgos.pokeyellow.core.di

import android.content.Context
import com.mauricioburgos.pokeyellow.core.platform.AppExecutor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import java.util.concurrent.Executors
import javax.inject.Singleton


@Module
class AppModule (private val context: Context){
    @Singleton
    @Provides
    @NotNull
    fun provideContext(): Context = context


    /**
     * AppExecutor.
     */
    @Provides
    @Singleton
    fun provideAppExecutor(): AppExecutor {
        return AppExecutor(
            Executors.newSingleThreadExecutor(),
            Executors.newSingleThreadExecutor(),
            Executors.newSingleThreadExecutor()
        )
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

}