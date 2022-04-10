package com.example.loginapp.ui.login

import com.example.loginapp.domain.LoginFormUsecase

class LoginFormViewModel(
    private val loginFormUsecase: LoginFormUsecase
) :
    LoginFormContract.ViewModel {

    private var view: LoginFormContract.View? = null

    private var isEnterSuccess = false

    private var isRestored = false

    override fun onViewAttach(
        view: LoginFormContract.View
    ) {
        this.view = view
        if (isRestored && isEnterSuccess)
            view.setUserLoginSuccess(
                "Вход выполнен",
                true
            )
        else if (isRestored && !isEnterSuccess)
            view.setUserLoginError(
                "Неверный логин или пароль!",
                true
            )
    }

    override fun onUserLogin(
        username: String,
        password: String
    ) {
        view?.showLoginProcessLoading(
            true
        )

        loginFormUsecase.userLogin(
            username,
            password
        ) { result ->
            view?.showLoginProcessLoading(
                false
            )
            if (result) {
                isEnterSuccess = true
                view?.setUserLoginSuccess(
                    "Вход выполнен"
                )
            } else {
                isEnterSuccess = false
                view?.setUserLoginError(
                    "Неверный логин или пароль!"
                )
            }
        }
    }

    override fun onUserRegistration() {
        view?.showUserRegistrationForm()
    }

    override fun onUserForgotPassword() {
        view?.showUserForgotPasswordForm()
    }

    override fun onRotatePresenterRestored(
        isRestored: Boolean
    ) {
        this.isRestored = isRestored
    }
}