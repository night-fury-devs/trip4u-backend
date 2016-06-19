package com.nfd.trip4u.controller

import com.nfd.trip4u.service.domain.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Author: Mary Kuchumova
 * Date: 19 Jun 2016
 * Time: 19:25
 */

@RestController
@RequestMapping(value = "/user")
open class UserController {

    @Autowired
    private lateinit var userService: UserService

    @RequestMapping(value = "/validateLogin", method = arrayOf(RequestMethod.GET))
    open fun validateLogin(login: String): Boolean {
        return userService.findByUserName(login) != null
    }

    @RequestMapping(value = "/validateEmail", method = arrayOf(RequestMethod.GET))
    open fun validateEmai(email: String): Boolean {
        return userService.findByEmail(email) != null
    }
}