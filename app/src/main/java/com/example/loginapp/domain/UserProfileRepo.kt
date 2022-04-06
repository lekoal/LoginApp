package com.example.loginapp.domain

import com.example.loginapp.domain.entities.UserProfileData

interface UserProfileRepo {
    fun addNewUser(userProfile: UserProfileData)

    fun getUser(username: String): UserProfileData

    fun getAllUsers(): List<UserProfileData>

    fun editUser(username: String, userProfile: UserProfileData)

    fun deleteUser(username: String)

    fun deleteAllUsers()
}