package com.example.loginapp

import java.lang.Thread.sleep

class LoginFormPresenter : LoginFormContract.Presenter {

    private var view: LoginFormContract.View? = null

    override fun onViewAttach(view: LoginFormContract.View) {
        this.view = view
    }

    override fun onEnter(username: String, password: String) {
        view?.showProcessLoading(true)
        Thread {
            sleep(2000)
            view?.getHandler()?.post {
                view?.showProcessLoading(false)
                if (username == "admin" && password == "admin") {
                    view?.setEnterSuccess("Вход выполнен")
                } else {
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