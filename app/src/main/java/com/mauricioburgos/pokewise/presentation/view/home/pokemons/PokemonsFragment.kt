package com.mauricioburgos.pokewise.presentation.view.home.pokemons

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
import com.mauricioburgos.pokewise.AppController
import com.mauricioburgos.pokewise.R
import com.mauricioburgos.pokewise.core.utils.CustomProgressDialog
import com.mauricioburgos.pokewise.core.utils.Utils
import com.mauricioburgos.pokewise.databinding.PokemonsFragmentBinding
import com.mauricioburgos.pokewise.domain.State
import com.mauricioburgos.pokewise.presentation.view.adapters.PokemonAdapter
import com.mauricioburgos.pokewise.presentation.view.home.HomeActivity
import com.mauricioburgos.pokewise.presentation.viewmodel.PokemonsViewModel
import io.reactivex.disposables.Disposable


class PokemonsFragment() : Fragment() {

    lateinit var navController: NavController
    lateinit var binding: PokemonsFragmentBinding
    val progressDialog = CustomProgressDialog()
    private lateinit var pokemonListAdapter: PokemonAdapter
    private var subscribe: Disposable? = null

    private val pokemonsViewModel: PokemonsViewModel by lazy {
        ViewModelProvider(this@PokemonsFragment).get(PokemonsViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppController.component.inject(this)
        observeError()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.pokemons_fragment, container, false)
        navController = findNavController()


        if(progressDialog.dialog!=null){
            progressDialog.dialog!!.dismiss()

        }

        (activity as HomeActivity).changeToolbarText(getString(R.string.all_pokemons))

        if (isOnline(context!!)) {
            binding.placeholderLayout.visibility = View.INVISIBLE

            initAdapter()
            initState()
            onPokemonClick()

        } else {
            Utils.displayMessage(
                "Error",
                "No hay conexión a internet",
                activity!!.supportFragmentManager!!
            )

            binding.placeholderLayout.visibility = View.VISIBLE

        }

        return binding.root
    }

    private fun initAdapter() {
        if(progressDialog.dialog!=null){
            progressDialog.dialog!!.dismiss()

        }
        pokemonListAdapter = PokemonAdapter { pokemonsViewModel.retry() }
        binding.pokemonRecyclerView.adapter = pokemonListAdapter
        pokemonsViewModel.newsList.observe(viewLifecycleOwner, Observer {
            pokemonListAdapter.submitList(it)
        })

    }

    private fun initState() {
        if(progressDialog.dialog!=null){
            progressDialog.dialog!!.dismiss()

        }

        pokemonsViewModel.getState().observe(viewLifecycleOwner, Observer { state ->
            if (pokemonsViewModel.listIsEmpty() && state == State.LOADING){
                progressDialog.show(
                    context!!
                )
            }
             else {
                if(progressDialog.dialog!=null){
                    progressDialog.dialog!!.dismiss()

                }
           }
            if (!pokemonsViewModel.listIsEmpty()) {
                pokemonListAdapter.setState(state ?: State.DONE)
            }
        })
    }

    private fun observeError() {
        pokemonsViewModel.getError().observe(this, Observer { state ->

            Utils.displayMessage("",state,activity!!.supportFragmentManager)
        })
    }

    private fun onPokemonClick() {
        subscribe = pokemonListAdapter.clickPokemonEvent
            .subscribe { position ->
                val bottomSheetFragment = PokemonDetailBottomSheet(position + 1)
                bottomSheetFragment.show(activity!!.supportFragmentManager, bottomSheetFragment.tag)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        subscribe?.dispose()
    }

    override fun onResume() {
        super.onResume()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        }
        return false
    }
}


