package com.example.loginapp

class LoginFormPresenter : LoginFormContract.Presenter {

    private var view: LoginFormContract.View? = null

    private var isEnterSuccess = false

    private var isRestored = false

    private val api: LoginFormApi = MockLoginFormApiImpl()

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
        Thread {
            val isSuccess = api.enter(username, password)
            view?.getHandler()?.post {
                view?.showProcessLoading(false)
                if (isSuccess) {
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

    override fun onRestored(isRestored: Boolean) {
        this.isRestored = isRestored
    }
}