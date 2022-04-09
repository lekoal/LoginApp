package com.example.loginapp.domain

import androidx.annotation.WorkerThread

interface LoginFormApi {
    @WorkerThread
    fun enter(username: String, password: String): Boolean

    @WorkerThread
    fun registration(username: String, password: String, email: String): Boolean

    @WorkerThread
    fun exit(): Boolean

    @WorkerThread
    fun forgotPassword(username: String): Boolean
}