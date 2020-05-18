package com.mauricioburgos.pokeyellow.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mauricioburgos.pokeyellow.R
import com.mauricioburgos.pokeyellow.databinding.ItemPokemonBinding
import com.mauricioburgos.pokeyellow.domain.Pokemon
import com.mauricioburgos.pokeyellow.domain.State
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class PokemonAdapter(private val retry: () -> Unit)
    : PagedListAdapter<Pokemon, RecyclerView.ViewHolder>(PokemonDiffCallback) {

    private val clickCensoSubject = PublishSubject.create<Pokemon>()
    private var state = State.LOADING
    val clickCensoEvent: Observable<Pokemon> = clickCensoSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        return PokemonHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
    class PokemonHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemons: Pokemon ?) {

            binding.tvPokemonName.text = pokemons!!.name

            //binding.ivPokemon.setImageURI("https://pokeres.bastionbot.org/images/pokemon/"+(adapterPosition+1).toString()+".png")

            binding.ivPokemon.setImageURI("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+(adapterPosition+1).toString()+".png")
        }
        companion object {
            fun create(parent: ViewGroup): PokemonHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<ItemPokemonBinding>(layoutInflater, R.layout.item_pokemon,parent, false)
                return PokemonHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PokemonHolder).bind(getItem(position))
    }


    companion object {
        val PokemonDiffCallback = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }

}