package com.mauricioburgos.pokewise.presentation.view.home.pokemons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
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
import com.mauricioburgos.pokewise.presentation.viewmodel.PokemonsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonDetailBottomSheet(private  val pokemonId: Int) : BottomSheetDialogFragment() {

    lateinit var binding: PokemonDetailBottomSheetBinding
    val progressDialog = CustomProgressDialog()
    private val adapter = PokemonTypeAdapter(mutableListOf())
    var  pokemonDetails: PokemonDetails? = null


    private val pokemonInfoViewModel: PokemonInfoViewModel by viewModels()



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
            Utils.displayMessage("Error",it.message!!, requireActivity().supportFragmentManager!!)
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
        progressDialog.show(requireContext())
    }

    private fun observePokemon ()
    {
        pokemonInfoViewModel.getMPokemon().observe(viewLifecycleOwner, Observer { pokemon ->
            progressDialog.dialog!!.dismiss()
            pokemonDetails=pokemon

            binding.apply {
               tvPokemonTitle.text = pokemon.name.capitalize()
               tvPokemonDistance.text = "#${(pokemonId).toString()}"
               ivPokemon.setImageURI("https://pokeres.bastionbot.org/images/pokemon/" + (pokemonId).toString() + ".png")
               tvHeightText.text = "${(pokemon.height * 10)} Cm"
               tvWeightText.text = "${(pokemon.weight / 10)} Kg"
            }
            adapter.setData(pokemon.types!!)
        })
    }

}