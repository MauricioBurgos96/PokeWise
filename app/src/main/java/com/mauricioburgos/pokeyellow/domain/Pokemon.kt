package com.mauricioburgos.pokeyellow.domain


import com.google.gson.annotations.SerializedName

data class Pokemon(@SerializedName("name")
                       val name: String = "",
                       @SerializedName("url")
                       val url: String = "")


data class PokemonResponse(@SerializedName("next")
                   val next: String = "",
                   @SerializedName("previous")
                   val previous: String = "",
                   @SerializedName("count")
                   val count: Int = 0,
                   @SerializedName("results")
                   val results: List<Pokemon>?)


