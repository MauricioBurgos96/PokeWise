package com.mauricioburgos.pokewise.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mauricioburgos.pokewise.R
import com.mauricioburgos.pokewise.core.utils.FontSingleton
import com.mauricioburgos.pokewise.databinding.ItemPokemonTypeBinding
import com.mauricioburgos.pokewise.databinding.ItemSavedPokemonBinding
import com.mauricioburgos.pokewise.domain.PokemonDetails
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class SavedPokemonAdapter(private val types: MutableList<PokemonDetails>)
    : RecyclerView.Adapter<SavedPokemonAdapter.PokemonTypeHolder>() {

    private val clickPokemonSubject = PublishSubject.create<PokemonDetails>()
    val clickPokemonEvent: Observable<PokemonDetails> = clickPokemonSubject


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonTypeHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemSavedPokemonBinding>(layoutInflater, R.layout.item_saved_pokemon,parent, false)
        return PokemonTypeHolder(binding)
    }

    override fun getItemCount(): Int = types.size

    override fun onBindViewHolder(holder: PokemonTypeHolder, position: Int) {
        val pokemonDetails = types[position]
        holder.binding.pokemon = pokemonDetails
        holder.binding.apply {

            tvPokemonName.typeface= FontSingleton.fontBold
            tvPokemonName.text= pokemonDetails.name.capitalize()
            ivPokemon.setImageURI("https://pokeres.bastionbot.org/images/pokemon/"+(pokemonDetails.id).toString()+".png")
            tvHeightText.text = "${(pokemonDetails.height * 10)} Cm"
            tvWeightText.text = "${(pokemonDetails.weight / 10)} Kg"

            ivDelete.setOnClickListener {
                clickPokemonSubject.onNext(pokemonDetails)
            }
        }



    }

    fun setData(dataList: List<PokemonDetails>) {
        this.types.clear()
        this.types.addAll(dataList)
        notifyDataSetChanged()

    }

    inner class PokemonTypeHolder(val binding: ItemSavedPokemonBinding) : RecyclerView.ViewHolder(binding.root)



}