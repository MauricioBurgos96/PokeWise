package com.mauricioburgos.pokewise.domain


import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


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

    @GET("pokemon/{id}/")
    fun getPokemonInfo(
        @Path("id") id: Int
    ):Call<PokemonDetails>


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