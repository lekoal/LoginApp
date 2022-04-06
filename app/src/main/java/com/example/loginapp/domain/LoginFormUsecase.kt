package com.example.loginapp.domain

import androidx.annotation.MainThread


interface LoginFormUsecase {
    fun enter(
        username: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit
    )
}