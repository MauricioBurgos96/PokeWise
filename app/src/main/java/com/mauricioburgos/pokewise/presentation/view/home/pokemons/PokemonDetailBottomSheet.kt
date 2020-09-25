package com.mauricioburgos.pokewise.presentation.view.home.pokemons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mauricioburgos.pokewise.R
import com.mauricioburgos.pokewise.core.utils.CustomProgressDialog
import com.mauricioburgos.pokewise.core.utils.FontSingleton
import com.mauricioburgos.pokewise.core.utils.Utils
import com.mauricioburgos.pokewise.databinding.PokemonDetailBottomSheetBinding
import com.mauricioburgos.pokewise.domain.PokemonDetails
import com.mauricioburgos.pokewise.presentation.view.adapters.PokemonTypeAdapter
import com.mauricioburgos.pokewise.presentation.viewmodel.PokemonInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

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
        binding = DataBindingUtil.inflate(inflater, R.layout.pokemon_detail_bottom_sheet,container,false)


            binding.apply{
            typesRecyclerView.adapter = adapter
            tvPokemonTitle.typeface = FontSingleton.fontBold
            tvPokemonDistance.typeface = FontSingleton.fontRegular

            btnSaveToTeam.setOnClickListener{
                if(pokemonDetails != null) {
                    CoroutineScope(IO).launch {
                        pokemonInfoViewModel.savePokemonDb(pokemonDetails!!)
                    }
                    dismiss()
                }
            }
        }

        pokemonInfoViewModel.error.observe(viewLifecycleOwner, Observer {
            progressDialog.dialog!!.dismiss()
            Utils.displayMessage("Error",it.message!!, activity!!.supportFragmentManager!!)
            dismiss()

        })

        observePokemon()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialog.dialog!!.dismiss()

    }

    override fun onResume() {
        super.onResume()
        pokemonInfoViewModel.loadPokemon(pokemonId)
        progressDialog.show(context!!)
    }

    private fun observePokemon ()
    {
        pokemonInfoViewModel.getMPokemon().observe(viewLifecycleOwner, Observer { pokemon ->
            progressDialog.dialog!!.dismiss()
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