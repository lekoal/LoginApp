package com.example.loginapp.domain

interface LoginFormApi {
    fun enter(username: String, password: String): Boolean
    fun registration(username: String, password: String, email: String): Boolean
    fun exit(): Boolean
    fun forgotPassword(username: String): Boolean
}