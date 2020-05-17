package com.mauricioburgos.pokeyellow.presentation.view.home.team

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mauricioburgos.pokeyellow.R
import com.mauricioburgos.pokeyellow.databinding.PokemonsFragmentBinding
import com.mauricioburgos.pokeyellow.databinding.TeamFragmentBinding
import com.mauricioburgos.pokeyellow.presentation.view.home.HomeActivity


class TeamFragment() : Fragment() {

    lateinit var navController: NavController


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
        navController = findNavController()

        (activity as HomeActivity).changeToolbarText(getString(R.string.my_team))



        return binding.root
    }



    override fun onResume() {
        super.onResume()

    }




}


