package com.mauricioburgos.pokeyellow.data.implementations

import com.mauricioburgos.pokeyellow.data.repositories.UserRepository
import com.mauricioburgos.pokeyellow.core.utils.NetworkHandler
import com.mauricioburgos.pokeyellow.core.utils.PreferencesHelper
import com.mauricioburgos.pokeyellow.domain.UserApi

class UserRepositoryImpl(
    private val networkHandler: NetworkHandler,
    private val userApi: UserApi,
    private val preferencesHelper: PreferencesHelper
): UserRepository {




    override fun getIsLogged(): Boolean {

return false
    }

    override fun clearLoggedUSer() {

    }


}