package com.example.loginapp.ui.login

import com.example.loginapp.domain.LoginFormUsecase
import com.example.loginapp.utils.LoginPublisher

class LoginFormViewModel(
    private val loginFormUsecase: LoginFormUsecase
) :
    LoginFormContract.ViewModel {
    override val showLoginProcessLoading: LoginPublisher<Boolean> =
        LoginPublisher()
    override val isUserLoginSuccess: LoginPublisher<Boolean> =
        LoginPublisher()
    override val loginErrorSuccessMessage: LoginPublisher<String> =
        LoginPublisher()
    override val onUserRegistration: LoginPublisher<String> =
        LoginPublisher()
    override val onUserForgotPassword: LoginPublisher<String> =
        LoginPublisher()

    override fun onUserLogin(
        username: String,
        password: String
    ) {
        showLoginProcessLoading.post(true)

        loginFormUsecase.userLogin(
            username,
            password
        ) { result ->
            showLoginProcessLoading.post(false)
            if (result) {
                isUserLoginSuccess.post(true)
                loginErrorSuccessMessage.post("Вход выполнен")
            } else {
                isUserLoginSuccess.post(false)
                loginErrorSuccessMessage.post("Неверный логин или пароль!")
            }
        }
    }

    override fun onUserRegistration() {
        onUserRegistration.post("Нажата кнопка регистрации!")
    }

    override fun onUserForgotPassword() {
        onUserForgotPassword.post("Нажата кнопка восстановления пароля!")
    }

}