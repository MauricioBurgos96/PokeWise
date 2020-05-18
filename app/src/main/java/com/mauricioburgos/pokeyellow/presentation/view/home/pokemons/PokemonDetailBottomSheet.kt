package com.mauricioburgos.pokeyellow.presentation.view.home.pokemons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mauricioburgos.pokeyellow.R
import com.mauricioburgos.pokeyellow.core.utils.FontSingleton
import com.mauricioburgos.pokeyellow.databinding.PokemonDetailBottomSheetBinding

class PokemonDetailBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: PokemonDetailBottomSheetBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = DataBindingUtil.inflate(inflater, R.layout.pokemon_detail_bottom_sheet, container, false)


        binding.tvPokemonTitle.typeface = FontSingleton.fontBold
        binding.tvPokemonDistance.typeface = FontSingleton.fontRegular


        return binding.root
    }





}