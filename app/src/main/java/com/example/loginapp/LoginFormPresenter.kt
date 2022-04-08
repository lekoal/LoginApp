package com.example.loginapp

import java.lang.Thread.sleep

class LoginFormPresenter : LoginFormContract.Presenter {

    private var view: LoginFormContract.View? = null

    private var isEnterSuccess = false

    override fun onViewAttach(view: LoginFormContract.View, isRestored: Boolean) {
        this.view = view
        if (isRestored && isEnterSuccess) view.setEnterSuccess("Вход выполнен", true)
        else if (isRestored && !isEnterSuccess) view.setEnterError(
            "Неверный логин или пароль!",
            true
        )
    }

    override fun onEnter(username: String, password: String) {
        view?.showProcessLoading(true)
        Thread {
            sleep(2000)
            view?.getHandler()?.post {
                view?.showProcessLoading(false)
                if (username == "admin" && password == "admin") {
                    isEnterSuccess = true
                    view?.setEnterSuccess("Вход выполнен")
                } else {
                    isEnterSuccess = false
                    view?.setEnterError("Неверный логин или пароль!")
                }
            }
        }.start()
    }

    override fun onRegistration() {
        view?.showRegistration()
    }

    override fun onForgotPassword() {
        view?.showForgotPassword()
    }
}