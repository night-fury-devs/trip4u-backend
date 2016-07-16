package com.nfd.trip4u.controller

import com.nfd.trip4u.controller.exception.BadRequestException
import com.nfd.trip4u.dto.UserDto
import com.nfd.trip4u.service.domain.UserService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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
    open fun validateLogin(@RequestParam login: String): Boolean {
        return userService.findByUsername(login) == null
    }

    @RequestMapping(value = "/isEmailAvailable", method = arrayOf(RequestMethod.GET))
    open fun validateEmail(@RequestParam email: String): Boolean {
        return userService.findByEmail(email) == null
    }

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    open fun returnUserInfo(@AuthenticationPrincipal username: String): UserDto? {
        return userService.findUserInfo(username)
    }

    @RequestMapping(method = arrayOf(RequestMethod.PATCH))
    open fun updateUserInfo(@AuthenticationPrincipal username: String, @RequestBody @Valid userDto: UserDto,
                            bindingResult: BindingResult) {
        if (bindingResult.hasErrors()) throw BadRequestException()
        try {
            userDto.username = username
            userService.updateUserInfo(userDto)
        } catch (ex: UsernameNotFoundException) {
            log.error("Can't update user info.", ex)
            //TODO: Return some code
        }
    }

    @RequestMapping(value = "/avatar", method = arrayOf(RequestMethod.PATCH))
    open fun updateUserAvatar(@AuthenticationPrincipal username: String, @RequestBody avatarUrl: String?) {
        userService.updateUserAvatar(username, avatarUrl)
    }
}