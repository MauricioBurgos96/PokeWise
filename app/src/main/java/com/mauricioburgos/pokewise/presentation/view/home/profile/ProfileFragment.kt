package com.mauricioburgos.pokewise.presentation.view.home.profile

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.mauricioburgos.pokewise.AppController
import com.mauricioburgos.pokewise.R
import com.mauricioburgos.pokewise.core.utils.Constants
import com.mauricioburgos.pokewise.core.utils.PreferencesHelper
import com.mauricioburgos.pokewise.databinding.PokemonsFragmentBinding
import com.mauricioburgos.pokewise.databinding.ProfileFragmentBinding
import com.mauricioburgos.pokewise.presentation.view.MainActivity
import com.mauricioburgos.pokewise.presentation.view.home.HomeActivity
import com.mauricioburgos.pokewise.presentation.viewmodel.SettingsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class ProfileFragment() : Fragment() {


    private val settingsViewModel: SettingsViewModel by lazy {
        ViewModelProvider(this@ProfileFragment).get(SettingsViewModel::class.java)
    }

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

        (activity as HomeActivity).changeToolbarText(getString(R.string.my_settings))

        binding.logout.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                settingsViewModel.deleteAllSavedPokemons()

            }
            preferencesHelper.putBoolean(Constants.PKEY_IS_LOGGED, false)
            finishAffinity(activity!!)
            val intent = Intent(activity, MainActivity::class.java)
            preferencesHelper.putBoolean(Constants.PKEY_IS_LOGGED, false)
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.clear()
            editor.commit()
            startActivity(intent)

        }

        binding.deleteTeam.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                settingsViewModel.deleteAllSavedPokemons()

            }
            Toast.makeText(context, "Se borro el equipo", Toast.LENGTH_SHORT).show()

        }

        return binding.root
    }



    override fun onResume() {
        super.onResume()

    }




}


