package com.mauricioburgos.pokewise.core.utils

import android.content.Context
import android.graphics.Typeface
import com.mauricioburgos.pokewise.AppController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class FontSingleton(private  val context: Context) {


    init {
        fontBold = Typeface.createFromAsset(
            context.assets,
            "fonts/trade_gothic_bold.ttf"
        )
        fontRegular = Typeface.createFromAsset(
            context.assets,
            "fonts/trade_gothic_regular.ttf"
        )

    }

    companion object {

        var fontBold: Typeface? = null
        var fontRegular: Typeface? = null
        private var mInstance: FontSingleton? = null

        fun getInstance(context: Context): FontSingleton {
            if (mInstance == null) {
                mInstance = FontSingleton(context)
            }
            return mInstance as FontSingleton
        }

        fun getmInstance(): FontSingleton? {
            return mInstance
        }

    }






}