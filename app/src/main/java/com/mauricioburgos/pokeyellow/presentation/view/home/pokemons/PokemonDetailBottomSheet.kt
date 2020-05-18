package com.mauricioburgos.pokeyellow.presentation.view.home.pokemons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mauricioburgos.pokeyellow.R
import com.mauricioburgos.pokeyellow.core.utils.CustomProgressDialog
import com.mauricioburgos.pokeyellow.core.utils.FontSingleton
import com.mauricioburgos.pokeyellow.core.utils.Utils
import com.mauricioburgos.pokeyellow.databinding.PokemonDetailBottomSheetBinding
import com.mauricioburgos.pokeyellow.domain.PokemonDetails
import com.mauricioburgos.pokeyellow.presentation.view.adapters.PokemonTypeAdapter
import com.mauricioburgos.pokeyellow.presentation.viewmodel.PokemonInfoViewModel
import com.mauricioburgos.pokeyellow.presentation.viewmodel.PokemonsViewModel

class PokemonDetailBottomSheet(private  val pokemonId: Int) : BottomSheetDialogFragment() {

    lateinit var binding: PokemonDetailBottomSheetBinding
    val progressDialog = CustomProgressDialog()
    private val adapter = PokemonTypeAdapter(mutableListOf())
    var  pokemonDetails: PokemonDetails? = null

    private val pokemonInfoViewModel: PokemonInfoViewModel by lazy {
        ViewModelProvider(this@PokemonDetailBottomSheet).get(PokemonInfoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = DataBindingUtil.inflate(inflater, R.layout.pokemon_detail_bottom_sheet, container, false)

        binding.typesRecyclerView.adapter = adapter

        binding.tvPokemonTitle.typeface = FontSingleton.fontBold
        binding.tvPokemonDistance.typeface = FontSingleton.fontRegular

        pokemonInfoViewModel.loadCensosPokemon(pokemonId)
        progressDialog.show(context!!)

        binding.btnSaveToTeam.setOnClickListener{
            if(pokemonDetails != null) {
                pokemonInfoViewModel.savePokemonDb(pokemonDetails!!)
            }
        }

        pokemonInfoViewModel.error.observe(viewLifecycleOwner, Observer {
            progressDialog.dialog.dismiss()
            Utils.displayMessage("Error",it.message!!, activity!!.supportFragmentManager!!)
            dismiss()

        })

        observePokemon()


        return binding.root
    }

    private fun observePokemon (){
        pokemonInfoViewModel!!.getMPokemon().observe(viewLifecycleOwner, Observer { pokemon ->
            progressDialog.dialog.dismiss()
            pokemonDetails=pokemon
            binding.tvPokemonTitle.text = pokemon.name.capitalize()
            binding.tvPokemonDistance.text = "#${(pokemonId).toString()}"
            binding.ivPokemon.setImageURI("https://pokeres.bastionbot.org/images/pokemon/"+(pokemonId).toString()+".png")

            binding.tvHeightText.text = "${(pokemon.height * 10)} Cm"
            binding.tvWeightText.text = "${(pokemon.weight / 10)} Kg"

            adapter.setData(pokemon.types!!)

        })

    }



}