package com.mauricioburgos.pokeyellow.core.di

import android.content.Context
import com.mauricioburgos.pokeyellow.framework.AuthorizationInterceptor
import com.mauricioburgos.pokeyellow.core.utils.PreferencesHelper
import com.mauricioburgos.pokeyellow.framework.ApiProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HelperModule {
    @Provides
    @Singleton
    fun providesPreferencesHelper(context: Context) = PreferencesHelper(context)

    @Provides
    @Singleton
    fun provideApiProvider(preferenceHelper: PreferencesHelper, context: Context): ApiProvider =
        ApiProvider(AuthorizationInterceptor(preferenceHelper), context)

}