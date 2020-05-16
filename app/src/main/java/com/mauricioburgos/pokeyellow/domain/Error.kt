package com.mauricioburgos.pokeyellow.domain

import com.google.gson.annotations.SerializedName

data class ErrorResponse (
    @SerializedName("code") val code : Int?,
    @SerializedName("message") val message : String?
){
    companion object{
        fun empty() = ErrorResponse(null, null)
    }
}