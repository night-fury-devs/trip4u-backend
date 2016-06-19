package com.nfd.trip4u.controller

import com.nfd.trip4u.entity.domain.User
import com.nfd.trip4u.service.domain.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

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

    @RequestMapping(value = "/register", method = arrayOf(RequestMethod.POST))
    @ResponseStatus(value = HttpStatus.OK)
    open fun register(userToCreate: User): HttpStatus {
        if(userService.findByEmail(userToCreate.email) != null || userService.findByUserName(userToCreate.userName) != null) {
            return HttpStatus.BAD_REQUEST
        }
        userService.save(userToCreate)
        //TODO: add sending verification email to registered user
        return HttpStatus.CREATED
    }

    @RequestMapping(value = "/validateLogin", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    open fun validateLogin(login: String): Boolean {
        return userService.findByUserName(login) != null
    }

    @RequestMapping(value = "/validateEmail", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    open fun validateEmai(email: String): Boolean {
        return userService.findByEmail(email) != null
    }

}