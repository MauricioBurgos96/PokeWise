package com.mauricioburgos.pokeyellow.presentation.view.home.pokemons

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mauricioburgos.pokeyellow.R
import com.mauricioburgos.pokeyellow.core.utils.CustomProgressDialog
import com.mauricioburgos.pokeyellow.databinding.PokemonsFragmentBinding
import com.mauricioburgos.pokeyellow.domain.Pokemon
import com.mauricioburgos.pokeyellow.domain.State
import com.mauricioburgos.pokeyellow.presentation.view.adapters.PokemonAdapter
import com.mauricioburgos.pokeyellow.presentation.view.home.HomeActivity
import com.mauricioburgos.pokeyellow.presentation.viewmodel.LoginViewModel
import com.mauricioburgos.pokeyellow.presentation.viewmodel.PokemonsViewModel


class PokemonsFragment() : Fragment() {

    lateinit var navController: NavController
    lateinit var binding: PokemonsFragmentBinding
    val progressDialog = CustomProgressDialog()
    private lateinit var pokemonListAdapter: PokemonAdapter

    private val pokemonsViewModel: PokemonsViewModel by lazy {
        ViewModelProvider(this@PokemonsFragment).get(PokemonsViewModel::class.java)
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
         binding = DataBindingUtil.inflate(inflater, R.layout.pokemons_fragment, container, false)
        navController = findNavController()

        (activity as HomeActivity).changeToolbarText(getString(R.string.all_pokemons))



        initAdapter()
        initState()


        return binding.root
    }


    private fun initAdapter() {
        pokemonListAdapter = PokemonAdapter{ pokemonsViewModel.retry() }
        binding.pokemonRecyclerView.adapter = pokemonListAdapter
        pokemonsViewModel.newsList.observe(viewLifecycleOwner, Observer {
            pokemonListAdapter.submitList(it)
        })

    }

    private fun initState() {
        pokemonsViewModel.getState().observe(viewLifecycleOwner, Observer { state ->
            if (pokemonsViewModel.listIsEmpty() && state == State.LOADING)  progressDialog.show(context!!) else  progressDialog.dialog.dismiss()
            if (!pokemonsViewModel.listIsEmpty()) {
                pokemonListAdapter.setState(state ?: State.DONE)
            }
        })
    }




    override fun onResume() {
        super.onResume()

    }




}


