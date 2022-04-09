package com.example.loginapp.domain

import androidx.annotation.WorkerThread

interface LoginFormApi {
    @WorkerThread
    fun userLogin(
        username: String,
        password: String
    ): Boolean

    @WorkerThread
    fun userRegistration(
        username: String,
        password: String,
        email: String
    ): Boolean

    @WorkerThread
    fun userLogout(): Boolean

    @WorkerThread
    fun userForgotPassword(
        username: String
    ): Boolean
}