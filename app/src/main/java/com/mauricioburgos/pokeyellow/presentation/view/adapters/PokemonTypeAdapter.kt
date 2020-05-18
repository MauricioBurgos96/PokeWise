package com.mauricioburgos.pokeyellow.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mauricioburgos.pokeyellow.R
import com.mauricioburgos.pokeyellow.databinding.ItemPokemonTypeBinding
import com.mauricioburgos.pokeyellow.domain.TypesItem


class PokemonTypeAdapter(private val types: MutableList<TypesItem>)
    : RecyclerView.Adapter<PokemonTypeAdapter.PokemonTypeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonTypeHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemPokemonTypeBinding>(layoutInflater, R.layout.item_pokemon_type,parent, false)
        return PokemonTypeHolder(binding)
    }

    override fun getItemCount(): Int = types.size

    override fun onBindViewHolder(holder: PokemonTypeHolder, position: Int) {
        val typesItem = types[position]
        holder.binding.types = typesItem



    }

    fun setData(dataList: List<TypesItem>) {
        this.types.clear()
        this.types.addAll(dataList)
        notifyDataSetChanged()

    }

    inner class PokemonTypeHolder(val binding: ItemPokemonTypeBinding) : RecyclerView.ViewHolder(binding.root)



}