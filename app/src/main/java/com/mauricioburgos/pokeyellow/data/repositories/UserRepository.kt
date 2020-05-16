package com.mauricioburgos.pokeyellow.data.repositories


interface UserRepository {
    fun clearLoggedUSer()
    fun getIsLogged(): Boolean
}