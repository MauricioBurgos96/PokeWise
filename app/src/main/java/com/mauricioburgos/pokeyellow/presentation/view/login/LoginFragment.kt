package com.mauricioburgos.pokeyellow.presentation.view.login

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mauricioburgos.pokeyellow.R
import com.mauricioburgos.pokeyellow.core.utils.FontSingleton
import com.mauricioburgos.pokeyellow.databinding.LoginFragmentBinding


class LoginFragment() : Fragment() {


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
        val binding: LoginFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)



        //binding.tvLoginLoginfrag.typeface = FontSingleton.monstserratBold
        //binding.addMovieButton.typeface = FontSingleton.monstserratMedium

        binding.btnLogin.typeface = FontSingleton.fontBold

        binding.ivBackgroundLoginfrag.setActualImageResource(R.drawable.ic_background)
        binding.ivLogoLoginfrag.setActualImageResource(R.drawable.ic_app_icon)


        return binding.root
    }




    // In our Fragment subclass.
    override fun onResume() {
        super.onResume()

    }




}


