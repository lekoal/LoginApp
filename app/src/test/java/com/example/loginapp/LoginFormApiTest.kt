package com.example.loginapp

import com.example.loginapp.data.MockLoginFormApiImpl
import com.example.loginapp.domain.LoginFormApi
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoginFormApiTest {
    private lateinit var api: LoginFormApi

    @Before
    fun setUp() {
        api = MockLoginFormApiImpl()
    }

    @Test
    fun userLoginSuccess_Test() {
        assertTrue(api.userLogin("test", "test"))
    }

    @Test
    fun userLoginDenied_Test() {
        assertFalse(api.userLogin("user", "user"))
    }

    @Test
    fun userRegistrationSuccess_Test() {
        assertTrue(api.userRegistration("user", "", ""))
    }

    @Test
    fun userRegistrationDenied_Test() {
        assertFalse(api.userRegistration("", "", ""))
    }

    @Test
    fun userLogout_Test() {
        assertTrue(api.userLogout())
    }

    @Test
    fun userForgotPassword_Test() {
        assertTrue(api.userForgotPassword(""))
    }
}