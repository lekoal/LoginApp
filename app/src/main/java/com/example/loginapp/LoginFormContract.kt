package com.example.loginapp

import android.os.Handler

class LoginFormContract {

    interface View {
        fun setEnterSuccess(enterSuccessText: String, isRestored: Boolean = false)
        fun setEnterError(enterErrorText: String, isRestored: Boolean = false)
        fun showProcessLoading(isLoading: Boolean)
        fun showRegistration()
        fun showForgotPassword()
        fun getHandler(): Handler
    }

    interface Presenter {
        fun onViewAttach(view: View, isRestored: Boolean)
        fun onEnter(username: String, password: String)
        fun onRegistration()
        fun onForgotPassword()
    }
}