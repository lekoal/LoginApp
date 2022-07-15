package com.example.loginapp.data

import com.example.loginapp.domain.UserProfileRepo
import com.example.loginapp.domain.entities.UserProfileData

class UserProfileRepoImpl(
    private val userList: MutableList<UserProfileData>
) : UserProfileRepo {

    override fun addNewUser(
        userProfile: UserProfileData
    ) {
        userList.add(userProfile)
    }

    override fun getUser(
        username: String
    ): UserProfileData? {
        return MockUsersData.getUser(username)
    }

    override fun getAllUsers(): List<UserProfileData> {
        return MockUsersData.getAllUsers()
    }

    override fun editUser(
        username: String,
        userProfile: UserProfileData
    ) {
        MockUsersData.editUser(username, userProfile)
    }

    override fun deleteUser(
        username: String
    ) {
        MockUsersData.deleteUser(username)
    }

    override fun deleteAllUsers() {
        MockUsersData.deleteAllUsers()
    }
}