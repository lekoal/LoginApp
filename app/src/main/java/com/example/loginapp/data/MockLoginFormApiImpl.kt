package com.example.loginapp.data

import com.example.loginapp.domain.LoginFormApi

class MockLoginFormApiImpl : LoginFormApi {

    override fun userLogin(
        username: String,
        password: String
    ): Boolean {
        Thread.sleep(2000)
        return username == "admin" && password == "admin"
    }

    override fun userRegistration(
        username: String,
        password: String,
        email: String
    ): Boolean {
        Thread.sleep(2000)
        return username.isNotEmpty()
    }

    override fun userLogout(): Boolean {
        Thread.sleep(1000)
        return true
    }

    override fun userForgotPassword(
        username: String
    ): Boolean {
        Thread.sleep(1000)
        return true
    }
}