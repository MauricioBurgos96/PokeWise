package com.mauricioburgos.pokewise.presentation.view.home

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mauricioburgos.pokewise.domain.UserSigninRequest

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class LoginUnitTest {

    var userSigninRequest =  UserSigninRequest("afsafsad@dsad.com","dasdsad")

    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertEquals(true,userSigninRequest.isEmailValid())
    }


    @Test
    fun passwordEmpty_CorrectPasswordSimple_ReturnsTrue() {
        assertEquals(true,userSigninRequest.isPasswordNotEmpty())
    }

}