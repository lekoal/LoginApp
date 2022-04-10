package com.example.loginapp.data

import androidx.annotation.MainThread
import com.example.loginapp.domain.LoginFormApi
import com.example.loginapp.domain.LoginFormUsecase


class LoginFormUsecaseImpl(
    private val api: LoginFormApi
) : LoginFormUsecase {
    override fun userLogin(
        username: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit
    ) {
        Thread {
            val result = api.userLogin(username, password)
            callback(result)
        }.start()
    }
}