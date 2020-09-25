package com.mauricioburgos.pokewise.domain


import com.google.gson.annotations.SerializedName

data class EvolutionChain(@SerializedName("chain")
                          val chain: Chain,
                          @SerializedName("baby_trigger_item")
                          val babyTriggerItem: String? = null,
                          @SerializedName("id")
                          val id: Int = 0)


data class Chain(@SerializedName("species")
                 val species: Species,
                 @SerializedName("evolves_to")
                 val evolvesTo: List<EvolvesToItem>?,
                 @SerializedName("is_baby")
                 val isBaby: Boolean = false)


data class Trigger(@SerializedName("name")
                   val name: String = "",
                   @SerializedName("url")
                   val url: String = "")


data class EvolvesToItem(@SerializedName("evolution_details")
                         val evolutionDetails: List<EvolutionDetailsItem>?,
                         @SerializedName("species")
                         val species: Species,
                         @SerializedName("evolves_to")
                         val evolvesTo: List<EvolvesToItem>?,
                         @SerializedName("is_baby")
                         val isBaby: Boolean = false)


data class Species(@SerializedName("name")
                   val name: String = "",
                   @SerializedName("url")
                   val url: String = "")


data class EvolutionDetailsItem(@SerializedName("item")
                                val item: String? = null,
                                @SerializedName("relative_physical_stats")
                                val relativePhysicalStats: String? = null,
                                @SerializedName("turn_upside_down")
                                val turnUpsideDown: Boolean = false,
                                @SerializedName("gender")
                                val gender: String? = null,
                                @SerializedName("min_happiness")
                                val minHappiness: String? = null,
                                @SerializedName("party_type")
                                val partyType: String? = null,
                                @SerializedName("held_item")
                                val heldItem: String? = null,
                                @SerializedName("known_move")
                                val knownMove: String? = null,
                                @SerializedName("min_beauty")
                                val minBeauty: String? = null,
                                @SerializedName("trade_species")
                                val tradeSpecies: String? = null,
                                @SerializedName("trigger")
                                val trigger: Trigger,
                                @SerializedName("needs_overworld_rain")
                                val needsOverworldRain: Boolean = false,
                                @SerializedName("party_species")
                                val partySpecies: String? = null,
                                @SerializedName("min_affection")
                                val minAffection: String? = null,
                                @SerializedName("known_move_type")
                                val knownMoveType: String? = null,
                                @SerializedName("time_of_day")
                                val timeOfDay: String = "",
                                @SerializedName("location")
                                val location: String? = null,
                                @SerializedName("min_level")
                                val minLevel: Int = 0)
