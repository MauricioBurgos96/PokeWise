package com.mauricioburgos.pokewise.core.diHilt

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mauricioburgos.pokewise.R
import com.mauricioburgos.pokewise.core.utils.PreferencesHelper
import com.mauricioburgos.pokewise.domain.PokemonApi
import com.mauricioburgos.pokewise.domain.UserApi
import com.mauricioburgos.pokewise.framework.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson:  Gson, @ApplicationContext appContext: Context, httpClientBuilder : OkHttpClient.Builder): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(appContext.getString(R.string.api_base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClientBuilder.build())
    }

    @Singleton
    @Provides
    fun provideAuthorizationInterceptor(preferenceHelper: PreferencesHelper): AuthorizationInterceptor {
        return AuthorizationInterceptor(preferenceHelper)
    }





    @Singleton
    @Provides
    fun providesHttpClientBuilder(@ApplicationContext appContext: Context, authorizationInterceptor : AuthorizationInterceptor): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(logging)
            .cache(null)
    }



    @Singleton
    @Provides
    fun providePokemonService(retrofit: Retrofit.Builder): PokemonApi {
        return retrofit
            .build()
            .create(PokemonApi::class.java)
    }


    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit.Builder): UserApi {
        return retrofit
            .build()
            .create(UserApi::class.java)
    }

}




















