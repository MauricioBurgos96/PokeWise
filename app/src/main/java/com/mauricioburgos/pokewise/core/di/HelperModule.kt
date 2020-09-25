package com.mauricioburgos.pokewise.core.di

import android.content.Context
import com.mauricioburgos.pokewise.framework.AuthorizationInterceptor
import com.mauricioburgos.pokewise.core.utils.PreferencesHelper
import com.mauricioburgos.pokewise.framework.ApiProvider
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