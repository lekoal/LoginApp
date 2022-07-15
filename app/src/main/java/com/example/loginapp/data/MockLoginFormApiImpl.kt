package com.example.loginapp.data

import com.example.loginapp.domain.LoginFormApi

class MockLoginFormApiImpl : LoginFormApi {

    override fun userLogin(
        username: String,
        password: String
    ): Boolean {
        Thread.sleep(2000)
        if (
            UserProfileRepoImpl(MockUsersData.userList).getUser(username) != null &&
            UserProfileRepoImpl(MockUsersData.userList).getUser(username)?.userPassword == password
        ) {
            return true
        }
        return false
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