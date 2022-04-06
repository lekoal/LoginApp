package com.example.loginapp

import android.os.CountDownTimer

class LoginFormPresenter : LoginFormContract.Presenter {

    private var view: LoginFormContract.View? = null

    override fun onViewAttach(view: LoginFormContract.View) {
        this.view = view
    }

    override fun onEnter(username: String, password: String) {
        view?.showProcessLoading(true)
        if (username == "admin" && password == "admin") {
            object : CountDownTimer(2000, 2000) {
                override fun onTick(p0: Long) {}
                override fun onFinish() {
                    view?.showProcessLoading(false)
                    view?.setEnterSuccess("Вход выполнен")
                }
            }.start()
        } else {
            object : CountDownTimer(2000, 2000) {
                override fun onTick(p0: Long) {}
                override fun onFinish() {
                    view?.showProcessLoading(false)
                    view?.setEnterError("Неверный логин или пароль!")
                }
            }.start()
        }
    }

    override fun onRegistration() {
        view?.showRegistration()
    }

    override fun onForgotPassword() {
        view?.showForgotPassword()
    }
}