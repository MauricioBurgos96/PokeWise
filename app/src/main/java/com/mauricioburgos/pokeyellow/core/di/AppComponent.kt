package com.mauricioburgos.pokeyellow.core.di


import com.mauricioburgos.pokeyellow.AppController
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        HelperModule::class,
        RepositoryModule::class

    ]
)

interface AppComponent {

    fun inject(target: AppController)



}