package com.example.loginapp

import com.example.loginapp.data.MockUsersData
import com.example.loginapp.data.UserProfileRepoImpl
import com.example.loginapp.domain.UserProfileRepo
import com.example.loginapp.domain.entities.UserProfileData
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserProfileRepoTest {
    private lateinit var userProfileRepo: UserProfileRepo

    @Mock
    private lateinit var userList: MutableList<UserProfileData>

    @Mock
    private lateinit var userProfile: UserProfileData

    private lateinit var userNewProfile: UserProfileData

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        userProfileRepo = UserProfileRepoImpl(userList)

        userNewProfile = UserProfileData(
            userId = "1",
            username = "test2",
            userPassword = "test2",
            userEmail = "test2@email.com"
        )
    }

    @Test
    fun addNewUser_Test() {
        userProfileRepo.addNewUser(userProfile)
        verify(userList).add(userProfile)
    }

    @Test
    fun getUser_Test() {
        assertSame(
            userProfileRepo.getUser("test"),
            MockUsersData.getUser("test")
        )
    }

    @Test
    fun getAllUsers_Test() {
        assertSame(
            userProfileRepo.getAllUsers(),
            MockUsersData.getAllUsers()
        )
    }

    @Test
    fun editUser_Test() {
        userProfileRepo.editUser("test", userNewProfile)
        assertNull(MockUsersData.getUser("test"))
        assertNotNull(MockUsersData.getUser("test2"))
    }

    @Test
    fun deleteUser_Test() {
        userProfileRepo.deleteUser("test2")
        assertNull(MockUsersData.getUser("test2"))
    }

    @Test
    fun deleteAllUsers_Test() {
        userProfileRepo.deleteAllUsers()
        assertNull(MockUsersData.getUser(anyString()))
    }
}