package com.mauricioburgos.pokeyellow.framework

import android.util.Log
import com.mauricioburgos.pokeyellow.core.platform.Either
import com.mauricioburgos.pokeyellow.core.platform.Failure
import com.mauricioburgos.pokeyellow.domain.ErrorResponse
import org.json.JSONObject
import retrofit2.Call

interface ApiRequest {
    fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(Failure.ServerError(
                    (response.body() as? ErrorResponse) ?: ErrorResponse(0, JSONObject(response.errorBody()?.string()).getString("message"))
                    ?: ErrorResponse.empty()
                ))
            }
        } catch (exception: Throwable) {
            Log.e("GSON", exception.message?:"message empty")
            Either.Left(Failure.ServerError(ErrorResponse(500,"Error del servidor")))
        }
    }
}