package com.mauricioburgos.pokeyellow

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.firebase.FirebaseApp
import com.mauricioburgos.pokeyellow.core.di.AppModule
import com.mauricioburgos.pokeyellow.core.utils.FontSingleton


class AppController : Application() {



    init {
        instance = this
    }


    companion object {
        private var instance: AppController? = null
        lateinit var component: AppComponent

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        FontSingleton.getInstance()
        Fresco.initialize(this)
        FirebaseApp.initializeApp(this)

        component = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
        component.inject(this)
    }

}