package com.example.loginapp

import android.os.Handler
import com.example.loginapp.data.LoginFormUsecaseImpl
import com.example.loginapp.domain.LoginFormApi
import com.example.loginapp.domain.LoginFormUsecase
import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LoginFormUsecaseTest {
    private lateinit var loginFormUsecase: LoginFormUsecase

    @Mock
    private lateinit var api: LoginFormApi

    @Mock
    private lateinit var handler: Handler

    @Mock
    private lateinit var callback: (Boolean) -> Unit

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        loginFormUsecase = LoginFormUsecaseImpl(api, handler)
    }

    @Test
    fun userLogin_Test() {
        loginFormUsecase.userLogin("", "", callback)
        verify(handler).post(anyOrNull())
    }
}