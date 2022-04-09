package com.example.loginapp

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.loginapp.data.LoginFormUsecaseImpl
import com.example.loginapp.data.MockLoginFormApiImpl
import com.example.loginapp.domain.LoginFormApi
import com.example.loginapp.domain.LoginFormUsecase

class App : Application() {
    private val loginFormApi:
            LoginFormApi by lazy {
        MockLoginFormApiImpl()
    }
    val loginFormUsecase:
            LoginFormUsecase by lazy {
        LoginFormUsecaseImpl(
            app.loginFormApi,
            Handler(
                Looper.getMainLooper()
            )
        )
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }