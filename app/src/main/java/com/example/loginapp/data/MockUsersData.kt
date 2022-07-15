package com.example.loginapp.data

import com.example.loginapp.domain.entities.UserProfileData

object MockUsersData {
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

    val userList: MutableList<UserProfileData> =
        mutableListOf(
            admin,
            test
        )

    fun getUser(
        username: String
    ): UserProfileData? {
        userList.forEach { user ->
            if (user.username == username) {
                return user
            }
        }
        return null
    }

    fun getAllUsers(): List<UserProfileData> {
        return userList
    }

    fun editUser(
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

    fun deleteUser(
        username: String
    ) {
        userList.forEachIndexed { index, user ->
            if (user.username == username) {
                userList.removeAt(index)
            }
        }
    }

    fun deleteAllUsers() {
        userList.clear()
    }
}