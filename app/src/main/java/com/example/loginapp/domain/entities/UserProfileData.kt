package com.example.loginapp.domain.entities

data class UserProfileData(
    val userId: String,
    val username: String,
    val userPassword: String,
    val userBirthDate: String? = null,
    val userEmail: String,
    val userPictureUrl: String? = null
)
