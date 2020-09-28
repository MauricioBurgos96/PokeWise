package com.mauricioburgos.pokewise

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.firebase.FirebaseApp
import com.mauricioburgos.pokewise.core.utils.FontSingleton
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppController : Application() {


    companion object {
        private var instance: AppController? = null
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        //FontSingleton.getInstance()
        Fresco.initialize(this)
        FirebaseApp.initializeApp(this)

    }

}