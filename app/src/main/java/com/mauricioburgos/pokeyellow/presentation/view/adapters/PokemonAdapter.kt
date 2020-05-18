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


class PokemonAdapter(private val pokemonClick: () -> Unit)
    : PagedListAdapter<Pokemon, PokemonAdapter.PokemonHolder>(PokemonDiffCallback) {

    private var state = State.LOADING
    private val clickPokemonSubject = PublishSubject.create<Pokemon>()
    val clickPokemonEvent: Observable<Pokemon> = clickPokemonSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemPokemonBinding>(layoutInflater, R.layout.item_pokemon,parent, false)
        return PokemonHolder(binding)
    }

    override fun getItemCount(): Int {
        return super.getItemCount()

    }
    class PokemonHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root)



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

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        val pokemon = (getItem(position))!!
        holder.binding.pokemon = pokemon

        //binding.ivPokemon.setImageURI("https://pokeres.bastionbot.org/images/pokemon/"+(adapterPosition+1).toString()+".png")
        holder.binding.ivPokemon.setImageURI("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+(position+1).toString()+".png")

        holder.binding.cwPokemon.setOnClickListener {
            clickPokemonSubject.onNext(pokemon)


        }
    }

}