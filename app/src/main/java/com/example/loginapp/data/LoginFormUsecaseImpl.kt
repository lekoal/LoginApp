package com.example.loginapp.data

import android.os.Handler
import androidx.annotation.MainThread
import com.example.loginapp.domain.LoginFormApi
import com.example.loginapp.domain.LoginFormUsecase


class LoginFormUsecaseImpl(
    private val api: LoginFormApi,
    private val handler: Handler
) : LoginFormUsecase {
    override fun enter(
        username: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit
    ) {
        Thread {
            val result = api.enter(username, password)
            handler.post {
                callback(result)
            }
        }.start()
    }
}