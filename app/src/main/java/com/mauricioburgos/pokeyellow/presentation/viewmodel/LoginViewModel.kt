package com.mauricioburgos.pokeyellow.presentation.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mauricioburgos.pokeyellow.AppController
import com.mauricioburgos.pokeyellow.core.platform.Failure
import com.mauricioburgos.pokeyellow.data.repositories.UserRepository
import com.mauricioburgos.pokeyellow.domain.ErrorResponse
import com.mauricioburgos.pokeyellow.domain.UserSigninRequest
import com.mauricioburgos.pokeyellow.usecases.LoginUseCase


import javax.inject.Inject

class LoginViewModel() : ViewModel() {

    var mEmailAddress = ObservableField<String>("")
    var mPassword = ObservableField<String>("")

    val logged: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val error : MutableLiveData<ErrorResponse> by lazy { MutableLiveData<ErrorResponse>() }

    private var userMutableLiveData = MutableLiveData<UserSigninRequest>()


    @Inject
    lateinit var loginUseCase: LoginUseCase

    @Inject
    lateinit var userRepository: UserRepository

    init {
        AppController.component.inject(this)

    }


    fun getLoginUserLiveData(): LiveData<UserSigninRequest> = userMutableLiveData

    fun getIsLogged(): Boolean{
        return userRepository.getIsLogged()
    }

    fun checkUserData(){
        val loginUser = UserSigninRequest(mEmailAddress.get(), mPassword.get())
        userMutableLiveData.value = loginUser
    }

    fun login() {

       var userSigninRequest= UserSigninRequest(mEmailAddress.get(), mPassword.get())
       loginUseCase.invoke(userSigninRequest)
       {
           it.either(
               { failure ->
                   logged.value = false
                   error.value= (failure as Failure.ServerError).errorResponse
                   failure
               },
               { user ->
                   logged.value = true
                   user
               }
           )
       }


    }



}
