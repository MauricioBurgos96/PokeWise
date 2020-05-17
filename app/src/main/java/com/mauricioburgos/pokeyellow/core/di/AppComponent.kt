package com.mauricioburgos.pokeyellow.core.di


import com.mauricioburgos.pokeyellow.AppController
import com.mauricioburgos.pokeyellow.presentation.viewmodel.LoginViewModel
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
    fun inject(target: LoginViewModel)



}