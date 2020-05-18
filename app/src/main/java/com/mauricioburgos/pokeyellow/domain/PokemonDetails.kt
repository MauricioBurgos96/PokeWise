package com.mauricioburgos.pokeyellow.domain


import com.google.gson.annotations.SerializedName

data class Ability(@SerializedName("name")
                   val name: String = "",
                   @SerializedName("url")
                   val url: String = "")


data class TypesItem(@SerializedName("slot")
                     val slot: Int = 0,
                     @SerializedName("type")
                     val type: Type)


data class VersionGroupDetailsItem(@SerializedName("level_learned_at")
                                   val levelLearnedAt: Int = 0,
                                   @SerializedName("version_group")
                                   val versionGroup: VersionGroup,
                                   @SerializedName("move_learn_method")
                                   val moveLearnMethod: MoveLearnMethod)


data class StatsItem(@SerializedName("stat")
                     val stat: Stat,
                     @SerializedName("base_stat")
                     val baseStat: Int = 0,
                     @SerializedName("effort")
                     val effort: Int = 0)


data class Type(@SerializedName("name")
                val name: String = "",
                @SerializedName("url")
                val url: String = "")





data class PokemonDetails(@SerializedName("location_area_encounters")
                          val locationAreaEncounters: String = "",
                          @SerializedName("types")
                          val types: List<TypesItem>?,
                          @SerializedName("base_experience")
                          val baseExperience: Int = 0,
                          @SerializedName("weight")
                          val weight: Int = 0,
                          @SerializedName("is_default")
                          val isDefault: Boolean = false,
                          @SerializedName("abilities")
                          val abilities: List<AbilitiesItem>?,
                          @SerializedName("species")
                          val species: Species,
                          @SerializedName("stats")
                          val stats: List<StatsItem>?,
                          @SerializedName("moves")
                          val moves: List<MovesItem>?,
                          @SerializedName("name")
                          val name: String = "",
                          @SerializedName("id")
                          val id: Int = 0,
                          @SerializedName("forms")
                          val forms: List<FormsItem>?,
                          @SerializedName("height")
                          val height: Int = 0,
                          @SerializedName("order")
                          val order: Int = 0)


data class AbilitiesItem(@SerializedName("is_hidden")
                         val isHidden: Boolean = false,
                         @SerializedName("ability")
                         val ability: Ability,
                         @SerializedName("slot")
                         val slot: Int = 0)


data class Stat(@SerializedName("name")
                val name: String = "",
                @SerializedName("url")
                val url: String = "")


data class FormsItem(@SerializedName("name")
                     val name: String = "",
                     @SerializedName("url")
                     val url: String = "")



data class Move(@SerializedName("name")
                val name: String = "",
                @SerializedName("url")
                val url: String = "")


data class VersionGroup(@SerializedName("name")
                        val name: String = "",
                        @SerializedName("url")
                        val url: String = "")


data class MoveLearnMethod(@SerializedName("name")
                           val name: String = "",
                           @SerializedName("url")
                           val url: String = "")


data class Species(@SerializedName("name")
                   val name: String = "",
                   @SerializedName("url")
                   val url: String = "")


data class MovesItem(@SerializedName("version_group_details")
                     val versionGroupDetails: List<VersionGroupDetailsItem>?,
                     @SerializedName("move")
                     val move: Move)


