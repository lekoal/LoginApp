package com.example.loginapp.ui.login

import com.example.loginapp.domain.LoginFormUsecase

class LoginFormPresenter(private val loginFormUsecase: LoginFormUsecase) :
    LoginFormContract.Presenter {

    private var view: LoginFormContract.View? = null

    private var isEnterSuccess = false

    private var isRestored = false

    override fun onViewAttach(view: LoginFormContract.View) {
        this.view = view
        if (isRestored && isEnterSuccess) view.setEnterSuccess(
            "Вход выполнен",
            true
        )
        else if (isRestored && !isEnterSuccess) view.setEnterError(
            "Неверный логин или пароль!",
            true
        )
    }

    override fun onEnter(username: String, password: String) {
        view?.showProcessLoading(true)

        loginFormUsecase.enter(username, password) { result ->
            view?.showProcessLoading(false)
            if (result) {
                isEnterSuccess = true
                view?.setEnterSuccess("Вход выполнен")
            } else {
                isEnterSuccess = false
                view?.setEnterError("Неверный логин или пароль!")
            }
        }
    }

    override fun onRegistration() {
        view?.showRegistration()
    }

    override fun onForgotPassword() {
        view?.showForgotPassword()
    }

    override fun onRestored(isRestored: Boolean) {
        this.isRestored = isRestored
    }
}