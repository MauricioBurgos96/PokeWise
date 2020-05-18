package com.mauricioburgos.pokeyellow.domain


import com.google.gson.annotations.SerializedName
import com.mauricioburgos.pokeyellow.framework.ApiResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

data class Pokemon(@SerializedName("name")
                       val name: String = "",
                       @SerializedName("url")
                       val url: String = "")




data class Response(
    @SerializedName("results") val results: List<Pokemon>
)


interface PokemonApi {

    @GET("pokemon")
    fun getPokemons(
        @Query("offset") page: Int, @Query("limit") pageSize: Int): Single<Response>

}

class GetPokemonsRequest(
    var offset : Int? = null,
    var limit : Int? = null

){
    fun toQueryMap() : Map<String, String>{
        val map = hashMapOf<String, String>()
        offset?.let { map.put("offset", offset.toString()) }
        limit?.let { map.put("limit", limit.toString()) }

        return map
    }
}