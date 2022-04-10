package com.example.loginapp.ui.login

import androidx.annotation.MainThread

interface LoginFormContract {

//        @MainThread
//        fun getHandler(): Handler

    interface ViewModel {

        @MainThread
        fun setUserLoginErrorSuccess(
            errorSuccessMessageText: String,
            isSuccess: Boolean,
            isRestored: Boolean = false
        )

        @MainThread
        fun showLoginProcessLoading(
            isLoading: Boolean
        )

        @MainThread
        fun showUserRegistrationForm()

        @MainThread
        fun showUserForgotPasswordForm()

        @MainThread
        fun onUserLogin(
            username: String,
            password: String
        )

        @MainThread
        fun onUserRegistration()

        @MainThread
        fun onUserForgotPassword()

        @MainThread
        fun onRotatePresenterRestored(
            isRestored: Boolean
        )
    }
}