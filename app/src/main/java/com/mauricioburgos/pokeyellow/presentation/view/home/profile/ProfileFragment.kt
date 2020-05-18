package com.mauricioburgos.pokeyellow.presentation.view.home.profile

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.firebase.iid.FirebaseInstanceId
import com.mauricioburgos.pokeyellow.AppController
import com.mauricioburgos.pokeyellow.R
import com.mauricioburgos.pokeyellow.core.utils.Constants
import com.mauricioburgos.pokeyellow.core.utils.PreferencesHelper
import com.mauricioburgos.pokeyellow.databinding.PokemonsFragmentBinding
import com.mauricioburgos.pokeyellow.databinding.ProfileFragmentBinding
import com.mauricioburgos.pokeyellow.presentation.view.MainActivity
import com.mauricioburgos.pokeyellow.presentation.view.home.HomeActivity
import java.io.IOException
import javax.inject.Inject


class ProfileFragment() : Fragment() {

    lateinit var navController: NavController

    @Inject
    lateinit var preferencesHelper: PreferencesHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppController.component.inject(this)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ProfileFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        navController = findNavController()

        (activity as HomeActivity).changeToolbarText(getString(R.string.my_settings))

        binding.logout.setOnClickListener {

            preferencesHelper.putBoolean(Constants.PKEY_IS_LOGGED, false)
            finishAffinity(activity!!)
            val intent = Intent(activity, MainActivity::class.java)
            preferencesHelper.putBoolean(Constants.PKEY_IS_LOGGED, false)
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.clear()
            editor.commit()
            startActivity(intent)

        }

        return binding.root
    }



    override fun onResume() {
        super.onResume()

    }




}


