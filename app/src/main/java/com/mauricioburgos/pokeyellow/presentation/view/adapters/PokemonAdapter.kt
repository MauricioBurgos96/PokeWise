package com.mauricioburgos.pokeyellow.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mauricioburgos.pokeyellow.R
import com.mauricioburgos.pokeyellow.databinding.ItemPokemonBinding
import com.mauricioburgos.pokeyellow.domain.Pokemon
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class PokemonAdapter(private val orders: MutableList<Pokemon>)
    : RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {

    private val clickCensoSubject = PublishSubject.create<Pokemon>()
    val clickCensoEvent: Observable<Pokemon> = clickCensoSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemPokemonBinding>(layoutInflater, R.layout.item_pokemon,parent, false)
        return PokemonHolder(binding)
    }

    override fun getItemCount(): Int = orders.size

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        val pokemon = orders[position]
        holder.binding.pokemon = pokemon



        holder.binding.cwPokemon.setOnClickListener {
            clickCensoSubject.onNext(pokemon)

        }

    }

    fun setOrders(movieList: List<Pokemon>) {
        this.orders.clear()
        this.orders.addAll(movieList)
        notifyDataSetChanged()

    }

    inner class PokemonHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root)



}