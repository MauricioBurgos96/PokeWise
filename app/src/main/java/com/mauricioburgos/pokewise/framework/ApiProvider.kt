package com.mauricioburgos.pokewise.framework

import android.content.Context
import com.mauricioburgos.pokewise.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiProvider(
    authorizationInterceptor: AuthorizationInterceptor,
    private val context: Context
) {
    private var retrofit: Retrofit

    companion object {

    }

    init {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(logging)
            .cache(null)

        retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.api_base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClientBuilder.build())
            .build()

    }

    fun <S> getEndpoint(serviceClass: Class<S>): S = retrofit.create(serviceClass)
}