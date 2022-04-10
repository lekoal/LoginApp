package com.example.loginapp.ui.login

import androidx.annotation.MainThread
import com.example.loginapp.utils.LoginPublisher

interface LoginFormContract {

    interface ViewModel {

        val showLoginProcessLoading: LoginPublisher<Boolean>
        val isUserLoginSuccess: LoginPublisher<Boolean>
        val loginErrorSuccessMessage: LoginPublisher<String>
        val onUserRegistration: LoginPublisher<String>
        val onUserForgotPassword: LoginPublisher<String>

        @MainThread
        fun onUserLogin(
            username: String,
            password: String
        )

        @MainThread
        fun onUserRegistration()

        @MainThread
        fun onUserForgotPassword()
    }
}