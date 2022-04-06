package com.example.loginapp

class LoginFormContract {

    interface View {
        fun setEnterSuccess(enterSuccessText: String)
        fun setEnterError(enterErrorText: String)
        fun showProcessLoading(isLoading: Boolean)
        fun showRegistration()
        fun showForgotPassword()
    }

    interface Presenter {
        fun onViewAttach(view: View)
        fun onEnter(username: String, password: String)
        fun onRegistration()
        fun onForgotPassword()
    }
}