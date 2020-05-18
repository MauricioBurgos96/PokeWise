package com.mauricioburgos.pokeyellow.presentation.view.home.team

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mauricioburgos.pokeyellow.R
import com.mauricioburgos.pokeyellow.databinding.PokemonsFragmentBinding
import com.mauricioburgos.pokeyellow.databinding.TeamFragmentBinding
import com.mauricioburgos.pokeyellow.presentation.view.adapters.PokemonTypeAdapter
import com.mauricioburgos.pokeyellow.presentation.view.adapters.SavedPokemonAdapter
import com.mauricioburgos.pokeyellow.presentation.view.home.HomeActivity
import com.mauricioburgos.pokeyellow.presentation.viewmodel.PokemonTeamViewModel
import com.mauricioburgos.pokeyellow.presentation.viewmodel.PokemonsViewModel


class TeamFragment() : Fragment() {
    private val adapter = SavedPokemonAdapter(mutableListOf())

    private val pokemonTeamViewModel: PokemonTeamViewModel by lazy {
        ViewModelProvider(this@TeamFragment).get(PokemonTeamViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // AppController.component.inject(this)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: TeamFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.team_fragment, container, false)

        (activity as HomeActivity).changeToolbarText(getString(R.string.my_team))

        observePokemonsSaved()
        binding.typesRecyclerView.adapter = adapter


        return binding.root
    }



    override fun onResume() {
        super.onResume()

    }


    private fun observePokemonsSaved (){
        pokemonTeamViewModel.getAllPokemons().observe(viewLifecycleOwner, Observer { pokemons ->
            Toast.makeText(context, "Tienes ${pokemons.size.toString()} pokemons guardados.", Toast.LENGTH_SHORT).show()
            adapter.setData(pokemons)

        })

    }



}


