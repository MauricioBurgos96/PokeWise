package com.mauricioburgos.pokeyellow.core.utils

import android.app.Activity
import android.graphics.Typeface
import com.mauricioburgos.pokeyellow.AppController

class FontSingleton {

    init {
        fontBold = Typeface.createFromAsset(
            AppController.applicationContext().assets,
            "fonts/roboto_bold.ttf"
        )
        fontRegular = Typeface.createFromAsset(
            AppController.applicationContext().assets,
            "fonts/roboto_regular.ttf"
        )
        fontMedium = Typeface.createFromAsset(
            AppController.applicationContext().assets,
            "fonts/roboto_medium.ttf"
        )

    }

    companion object {

        var fontBold: Typeface? = null
        var fontRegular: Typeface? = null
        var fontMedium: Typeface? = null
        private var mInstance: FontSingleton? = null

        fun getInstance(): FontSingleton {
            if (mInstance == null) {
                mInstance = FontSingleton()
            }
            return mInstance as FontSingleton
        }



        fun getmInstance(): FontSingleton? {
            return mInstance
        }




    }






}