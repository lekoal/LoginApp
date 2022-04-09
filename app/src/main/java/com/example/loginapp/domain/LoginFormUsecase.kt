package com.example.loginapp.domain

import androidx.annotation.MainThread


interface LoginFormUsecase {
    fun userLogin(
        username: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit
    )
}