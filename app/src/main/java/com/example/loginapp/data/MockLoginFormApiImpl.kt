package com.example.loginapp.data

import com.example.loginapp.domain.LoginFormApi

class MockLoginFormApiImpl : LoginFormApi {

    override fun enter(username: String, password: String): Boolean {
        Thread.sleep(2000)
        return username == "admin" && password == "admin"
    }

    override fun registration(username: String, password: String, email: String): Boolean {
        Thread.sleep(2000)
        return username.isNotEmpty()
    }

    override fun exit(): Boolean {
        Thread.sleep(1000)
        return true
    }

    override fun forgotPassword(username: String): Boolean {
        Thread.sleep(1000)
        return true
    }
}