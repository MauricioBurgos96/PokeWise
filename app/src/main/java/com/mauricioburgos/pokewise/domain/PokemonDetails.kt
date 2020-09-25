package com.mauricioburgos.pokewise.domain


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName




data class TypesItem(@SerializedName("slot")
                     val slot: Int = 0,
                     @SerializedName("type")
                     val type: Type)


data class Type(@SerializedName("name")
                val name: String = "",
                @SerializedName("url")
                val url: String = "")



@Entity(tableName = "team_pokemons")
data class PokemonDetails(
                          @SerializedName("id")
                          @PrimaryKey @ColumnInfo(name = "id") val id: Int = 0,
                          @SerializedName("types")
                          @ColumnInfo(name = "types") val types: List<TypesItem>? = null,
                          @SerializedName("weight")
                          @ColumnInfo(name = "weight") val weight: Float = 0f,
                          @SerializedName("name")
                          @ColumnInfo(name = "name") val name: String = "",
                          @SerializedName("height")
                          @ColumnInfo(name = "height") val height: Int = 0,
                          @SerializedName("order")
                          @ColumnInfo(name = "order") val order: Int = 0)
{
    companion object {
        fun empty() = PokemonDetails()
    }
}

















