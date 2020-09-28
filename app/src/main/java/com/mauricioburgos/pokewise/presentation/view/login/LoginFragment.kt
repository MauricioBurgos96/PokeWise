package com.mauricioburgos.pokewise.presentation.view.login

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mauricioburgos.pokewise.R
import com.mauricioburgos.pokewise.core.utils.CustomProgressDialog
import com.mauricioburgos.pokewise.core.utils.FontSingleton
import com.mauricioburgos.pokewise.core.utils.Utils
import com.mauricioburgos.pokewise.databinding.LoginFragmentBinding
import com.mauricioburgos.pokewise.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment() : Fragment() {

    lateinit var navController: NavController
    val progressDialog = CustomProgressDialog()


   private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: LoginFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)

        requireActivity().window.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_background))
       binding.apply {

           viewModel = loginViewModel
           btnLogin.typeface = FontSingleton.fontBold
           ivLogoLoginfrag.setActualImageResource(R.drawable.ic_app_icon)

       }


        navController = findNavController()

       if(loginViewModel.getIsLogged()){
            requireActivity().finish()
            navController.navigate(R.id.action_loginFragment_to_homeActivity)

       }

        configureLiveDataObservers()



       loginViewModel.logged.observe(viewLifecycleOwner, Observer {
           progressDialog.dialog!!.dismiss()


           if(it){
                requireActivity().finish()
                navController.navigate(R.id.action_loginFragment_to_homeActivity)
            }
       }
       )
       loginViewModel.error.observe(viewLifecycleOwner, Observer {
           progressDialog.dialog!!.dismiss()
           Utils.displayMessage("Error",it.message!!, requireActivity().supportFragmentManager)

       })


        return binding.root
    }


    private fun configureLiveDataObservers() {
    loginViewModel.getLoginUserLiveData().observe(viewLifecycleOwner, Observer { user ->
        if ((user).username.isNullOrEmpty()) {
            Utils.displayMessage("Error","Ingrese un correo electrónico ", requireActivity().supportFragmentManager)

        } else if (!(user).isEmailValid()) {
            Utils.displayMessage("Error","Correo electrónico invalido", requireActivity().supportFragmentManager)

        } else if ((user).password.isNullOrEmpty()) {
            Utils.displayMessage("Error","Ingrese contraseña", requireActivity().supportFragmentManager)

        }
        else {
            progressDialog.show(requireContext())
            loginViewModel.login()

        }

    })

    }


    // In our Fragment subclass.
    override fun onResume() {
        super.onResume()

    }




}
