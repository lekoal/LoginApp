package com.example.loginapp.ui.login

import android.os.Handler
import androidx.annotation.MainThread

class LoginFormContract {

    interface View {
        @MainThread
        fun setUserLoginSuccess(
            enterSuccessText: String,
            isRestored: Boolean = false
        )

        @MainThread
        fun setUserLoginError(
            enterErrorText: String,
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
        fun getHandler(): Handler
    }

    interface Presenter {
        @MainThread
        fun onViewAttach(
            view: View
        )

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