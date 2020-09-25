package com.mauricioburgos.pokewise.framework

import com.mauricioburgos.pokewise.core.utils.Constants
import com.mauricioburgos.pokewise.core.utils.PreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val preferencesHelper: PreferencesHelper) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
            .method(original.method(), original.body())

        preferencesHelper.getString(Constants.PKEY_TOKEN)?.let {
            builder.header("Authorization", "Bearer $it")
        }

        return chain.proceed(builder.build())
    }

}