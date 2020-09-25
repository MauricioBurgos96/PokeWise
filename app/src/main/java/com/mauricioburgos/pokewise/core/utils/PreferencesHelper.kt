package com.mauricioburgos.pokewise.core.utils

import android.content.Context

class PreferencesHelper(context: Context) {

    private val  mPREFERENCES = Constants.PREFERENCES_TAG
    private val sharedPreferences = context.getSharedPreferences(mPREFERENCES, Context.MODE_PRIVATE)

    fun getString(name: String): String? = sharedPreferences.getString(name, null)

    fun putString(name: String, value: String?) =
        sharedPreferences.edit().putString(name, value).apply()

    fun putBoolean(name: String, value: Boolean) = sharedPreferences.edit().putBoolean(name, value).apply()

    fun getBoolean(name: String): Boolean = sharedPreferences.getBoolean(name, false)

    fun putInteger(name: String, value: Int) = sharedPreferences.edit().putInt(name, value).apply()

    fun getInteger(name: String) = sharedPreferences.getInt(name, -1)

}