package com.nfd.trip4u.controller

import com.nfd.trip4u.entity.domain.User
import com.nfd.trip4u.service.domain.UserService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Author: Mary Kuchumova
 * Date: 19 Jun 2016
 * Time: 19:25
 */

@RestController
@RequestMapping(value = "/user")
open class UserController {

    private val log = LogFactory.getLog(this.javaClass)

    @Autowired
    private lateinit var userService: UserService

    @RequestMapping(value = "/isLoginAvailable", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    open fun validateLogin(@RequestParam login: String): Boolean {
        return userService.findByUserName(login) == null
    }

    @RequestMapping(value = "/isEmailAvailable", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    open fun validateEmail(@RequestParam email: String): Boolean {
        return userService.findByEmail(email) == null
    }

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    open fun getUserInfo(@PathVariable id: String): User? {
        return userService.findById(id)
    }

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    @ResponseBody
    open fun getUserInfoByToken(@RequestParam token: String): User? {
        //TODO: add token based implementation
        return null
    }

    @RequestMapping(value = "/token", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    open fun token(@RequestParam userName: String, @RequestParam password: String){
        //TODO: add implementation
    }
}