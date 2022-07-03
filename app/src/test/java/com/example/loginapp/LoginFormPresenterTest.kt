package com.example.loginapp

import com.example.loginapp.domain.LoginFormUsecase
import com.example.loginapp.ui.login.LoginFormContract
import com.example.loginapp.ui.login.LoginFormPresenter
import com.nhaarman.mockito_kotlin.anyOrNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class LoginFormPresenterTest {
    private lateinit var presenter: LoginFormPresenter

    @Mock
    private lateinit var loginFormUsecase: LoginFormUsecase

    @Mock
    private lateinit var view: LoginFormContract.View

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = LoginFormPresenter(loginFormUsecase)
        presenter.onViewAttach(view)
    }

    @Test
    fun processLoadingIsShowing_Test() {
        presenter.onUserLogin("", "")
        verify(view).showLoginProcessLoading(anyBoolean())
    }

    @Test
    fun userSuccessLogin_Test() {
        presenter.onUserLogin("", "")
        verify(loginFormUsecase).userLogin(anyString(), anyString(), anyOrNull())
    }

    @Test
    fun userRegistration_Test() {
        presenter.onUserRegistration()
        verify(view).showUserRegistrationForm()
    }

    @Test
    fun userForgotPassword_Test() {
        presenter.onUserForgotPassword()
        verify(view).showUserForgotPasswordForm()
    }

}