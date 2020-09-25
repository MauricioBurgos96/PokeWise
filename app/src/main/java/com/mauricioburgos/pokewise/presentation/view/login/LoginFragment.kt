package com.mauricioburgos.pokewise.presentation.view.login

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mauricioburgos.pokewise.R
import com.mauricioburgos.pokewise.core.utils.CustomProgressDialog
import com.mauricioburgos.pokewise.core.utils.FontSingleton
import com.mauricioburgos.pokewise.core.utils.Utils
import com.mauricioburgos.pokewise.databinding.LoginFragmentBinding
import com.mauricioburgos.pokewise.presentation.viewmodel.LoginViewModel


class LoginFragment() : Fragment() {

    lateinit var navController: NavController
    val progressDialog = CustomProgressDialog()
    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this@LoginFragment).get(LoginViewModel::class.java)
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
        val binding: LoginFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        activity!!.window.setBackgroundDrawable(ContextCompat.getDrawable(context!!,R.drawable.ic_background))
        binding.viewModel = loginViewModel
        navController = findNavController()

        if(loginViewModel.getIsLogged()){
             activity!!.finish()
             navController.navigate(R.id.action_loginFragment_to_homeActivity)

        }

        configureLiveDataObservers()

        binding.btnLogin.typeface = FontSingleton.fontBold
        binding.ivLogoLoginfrag.setActualImageResource(R.drawable.ic_app_icon)

        loginViewModel.logged.observe(viewLifecycleOwner, Observer {
            progressDialog.dialog!!.dismiss()


            if(it){
                 activity!!.finish()
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
                Utils.displayMessage("Error","Ingrese un correo electrónico ", activity!!.supportFragmentManager)

            } else if (!(user).isEmailValid()) {
                Utils.displayMessage("Error","Correo electrónico invalido", activity!!.supportFragmentManager)

            } else if ((user).password.isNullOrEmpty()) {
                Utils.displayMessage("Error","Ingrese contraseña", activity!!.supportFragmentManager)

            }
            else {
                progressDialog.show(context!!)
                loginViewModel.login()

            }

        })

    }


    // In our Fragment subclass.
    override fun onResume() {
        super.onResume()

    }




}
