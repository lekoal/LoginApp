package com.example.loginapp.data

import com.example.loginapp.domain.UserProfileRepo
import com.example.loginapp.domain.entities.UserProfileData

class UserProfileRepoImpl : UserProfileRepo {
    override fun addNewUser(
        userProfile: UserProfileData
    ) {
        TODO("Not yet implemented")
    }

    override fun getUser(
        username: String
    ): UserProfileData {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(): List<UserProfileData> {
        TODO("Not yet implemented")
    }

    override fun editUser(
        username: String,
        userProfile: UserProfileData
    ) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(
        username: String
    ) {
        TODO("Not yet implemented")
    }

    override fun deleteAllUsers() {
        TODO("Not yet implemented")
    }
}