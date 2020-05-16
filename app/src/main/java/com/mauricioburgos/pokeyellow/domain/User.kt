package com.mauricioburgos.pokeyellow.domain

import android.util.Patterns
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*


data class User(
    @SerializedName("access") val access: String?,
    @SerializedName("userId") val userId: String?,
    @SerializedName("refresh") val refresh:  String?,
    @SerializedName("displayName") val displayName: String?,
    @SerializedName("type") val type: String?){

    companion object {
        fun empty() =
            User(null, null, null, null, null)
    }

}


interface UserApi {

    @POST("/auth/token/")
    fun authenticate(
        @Body request: UserSigninRequest
    ): Call<User>

}


data class UserSigninRequest(
   var username: String? = null,
   var password: String?
) {
    companion object {
        fun empty() = UserSigninRequest(null, null)
    }

    fun isPasswordNotEmpty(): Boolean {
        return password!!.isNotEmpty()
    }

    fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(username).matches()
    }


}
