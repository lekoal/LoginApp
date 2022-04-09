package com.example.loginapp.data

import com.example.loginapp.domain.UserProfileRepo
import com.example.loginapp.domain.entities.UserProfileData

object UserProfileRepoImpl : UserProfileRepo {

    private val admin: UserProfileData =
        UserProfileData(
            userId = "0",
            username = "admin",
            userPassword = "admin",
            userEmail = "admin@email.com"
        )

    private val test: UserProfileData =
        UserProfileData(
            userId = "1",
            username = "test",
            userPassword = "test",
            userEmail = "test@email.com"
        )

    private val userList: MutableList<UserProfileData> =
        mutableListOf(
            admin,
            test
        )

    override fun addNewUser(
        userProfile: UserProfileData
    ) {
        userList.add(userProfile)
    }

    override fun getUser(
        username: String
    ): UserProfileData? {
        userList.forEach { user ->
            if (user.username == username) {
                return user
            }
        }
        return null
    }

    override fun getAllUsers(): List<UserProfileData> {
        return userList
    }

    override fun editUser(
        username: String,
        userProfile: UserProfileData
    ) {
        userList.forEachIndexed { index, user ->
            if (user.username == username) {
                userList.removeAt(index)
                userList.add(index, userProfile)
            }
        }
    }

    override fun deleteUser(
        username: String
    ) {
        userList.forEachIndexed { index, user ->
            if (user.username == username) {
                userList.removeAt(index)
            }
        }
    }

    override fun deleteAllUsers() {
        userList.clear()
    }
}