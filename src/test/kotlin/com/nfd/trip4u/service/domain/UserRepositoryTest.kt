package com.nfd.trip4u.service.domain

import com.nfd.trip4u.AbstractTestCase
import com.nfd.trip4u.entity.domain.Gender
import com.nfd.trip4u.entity.domain.Role
import com.nfd.trip4u.entity.domain.User
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 17 Jun 2016
 * Time: 22:56
 */

class UserRepositoryTest : AbstractTestCase() {

    private lateinit var user: User

    @Autowired
    private lateinit var userService: UserService

    @Before
    fun beforeRepositoryTestStart() {
        user = User(null, "test user", "test@mail.com", "passwrd", null, null, null, null, Gender.MALE, null,
                ArrayList<Role>(), true)
        user = userService.save(user)
        Assert.assertNotNull(user.id)
    }

    @Test
    fun testUpdateUser(){
        user.firstName = "name"
        val newUser = userService.save(user)
        Assert.assertEquals(newUser.firstName, user.firstName)
    }

    @Test
    fun testFindAllUsers(){
        val userList = userService.findAll()
        Assert.assertTrue(userList.contains(user))
    }

    @Test
    fun testFindUserById(){
        val foundUser = userService.findById(user.id)
        Assert.assertEquals(foundUser, user)
    }

    @Test
    fun testFindUserByUsername(){
        val foundUser = userService.findByUserName(user.userName)
        Assert.assertEquals(foundUser, user)
    }

    @Test
    fun testFindUserByEmail(){
        val foundUser = userService.findByEmail(user.email)
        Assert.assertEquals(foundUser, user)
    }

    @Test
    fun findUserByCredentials(){
        val foundUser = userService.findByUserNameAndPassword(user.userName, user.password)
        Assert.assertEquals(foundUser, user)
    }

    @Test
    fun testUsersCount(){
        val usersCount = userService.count()
        Assert.assertNotEquals(usersCount, 0)
    }

    @After
    fun afterRepositoryTestFinish() {
        userService.delete(user)
    }
}