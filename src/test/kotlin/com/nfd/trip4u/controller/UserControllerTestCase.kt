package com.nfd.trip4u.controller

import com.nfd.trip4u.AbstractTestCase
import com.nfd.trip4u.entity.domain.Gender
import com.nfd.trip4u.entity.domain.Role
import com.nfd.trip4u.entity.domain.User
import com.nfd.trip4u.service.domain.UserService
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 25 Jun 2016
 * Time: 22:07
 */


@WebAppConfiguration

open class UserControllerTestCase: AbstractTestCase() {

    private lateinit var user: User

    @Autowired
    private lateinit var mockMVC: MockMvc

    @Autowired
    private lateinit var userService: UserService

    @Before
    fun populateTestData() {
        user = User(null, "test user2", "test@mail.com2", "passwrd2", null, null, null, null,
                Gender.MALE, null,
                ArrayList<Role>(), true)
        user = userService.save(user)
        Assert.assertNotNull(user.id)
    }

    @Test
    fun validateLogin(){

    }

    @Test
    fun validateEmail(){

    }

    @Test
    fun getUserInfo(){

    }


    @After
    fun removeTestData() {
        userService.delete(user)
    }

}