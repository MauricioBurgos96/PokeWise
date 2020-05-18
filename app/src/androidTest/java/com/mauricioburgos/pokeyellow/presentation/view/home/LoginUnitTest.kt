package com.mauricioburgos.pokeyellow.presentation.view.home

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mauricioburgos.pokeyellow.domain.UserSigninRequest
import io.mockk.mockk

import io.reactivex.Observer
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
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