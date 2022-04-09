package com.example.loginapp.data

import android.os.Handler
import androidx.annotation.MainThread
import com.example.loginapp.domain.LoginFormApi
import com.example.loginapp.domain.LoginFormUsecase


class LoginFormUsecaseImpl(
    private val api: LoginFormApi,
    private val handler: Handler
) : LoginFormUsecase {
    override fun userLogin(
        username: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit
    ) {
        Thread {
            val result = api.userLogin(username, password)
            handler.post {
                callback(result)
            }
        }.start()
    }
}