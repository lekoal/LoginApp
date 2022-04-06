package com.example.loginapp

import android.app.Application
import android.content.Context
import com.example.loginapp.data.MockLoginFormApiImpl
import com.example.loginapp.domain.LoginFormApi

class App : Application() {
    val api: LoginFormApi by lazy { MockLoginFormApiImpl() }
}

val Context.app: App
    get() {
        return applicationContext as App
    }