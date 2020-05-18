package com.mauricioburgos.pokeyellow.framework

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(val data: T,
                          @SerializedName("current_page") val currentPage: Int,
                          @SerializedName("next") val nextPage : String?,
                          @SerializedName("previous") val lastPage : Int,
                          @SerializedName("count") val totalRows: Int)