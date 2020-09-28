package com.mauricioburgos.pokewise.core.diHilt

import android.content.Context
import com.mauricioburgos.pokewise.core.utils.PreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object HelperModule {

    @Singleton
    @Provides
    fun providesPreferencesHelper(@ApplicationContext appContext: Context) = PreferencesHelper(appContext)




}