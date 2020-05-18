package com.mauricioburgos.pokeyellow.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mauricioburgos.pokeyellow.R
import com.mauricioburgos.pokeyellow.core.utils.FontSingleton
import com.mauricioburgos.pokeyellow.databinding.ItemPokemonTypeBinding
import com.mauricioburgos.pokeyellow.databinding.ItemSavedPokemonBinding
import com.mauricioburgos.pokeyellow.domain.PokemonDetails


class SavedPokemonAdapter(private val types: MutableList<PokemonDetails>)
    : RecyclerView.Adapter<SavedPokemonAdapter.PokemonTypeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonTypeHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemSavedPokemonBinding>(layoutInflater, R.layout.item_saved_pokemon,parent, false)
        return PokemonTypeHolder(binding)
    }

    override fun getItemCount(): Int = types.size

    override fun onBindViewHolder(holder: PokemonTypeHolder, position: Int) {
        val pokemonDetails = types[position]
        holder.binding.pokemon = pokemonDetails
        holder.binding.tvPokemonName.typeface= FontSingleton.fontBold
        holder.binding.tvPokemonName.text= pokemonDetails.name.capitalize()

        holder.binding.ivPokemon.setImageURI("https://pokeres.bastionbot.org/images/pokemon/"+(pokemonDetails.id).toString()+".png")
        holder.binding.tvHeightText.text = "${(pokemonDetails.height * 10)} Cm"
        holder.binding.tvWeightText.text = "${(pokemonDetails.weight / 10)} Kg"


    }

    fun setData(dataList: List<PokemonDetails>) {
        this.types.clear()
        this.types.addAll(dataList)
        notifyDataSetChanged()

    }

    inner class PokemonTypeHolder(val binding: ItemSavedPokemonBinding) : RecyclerView.ViewHolder(binding.root)



}