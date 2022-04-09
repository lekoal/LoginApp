package com.example.loginapp.ui.login

import android.os.Handler
import androidx.annotation.MainThread

class LoginFormContract {

    interface View {
        @MainThread
        fun setEnterSuccess(enterSuccessText: String, isRestored: Boolean = false)

        @MainThread
        fun setEnterError(enterErrorText: String, isRestored: Boolean = false)

        @MainThread
        fun showProcessLoading(isLoading: Boolean)

        @MainThread
        fun showRegistration()

        @MainThread
        fun showForgotPassword()

        @MainThread
        fun getHandler(): Handler
    }

    interface Presenter {
        @MainThread
        fun onViewAttach(view: View)

        @MainThread
        fun onEnter(username: String, password: String)

        @MainThread
        fun onRegistration()

        @MainThread
        fun onForgotPassword()

        @MainThread
        fun onRestored(isRestored: Boolean)
    }
}