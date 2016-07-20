package com.nfd.trip4u.controller

import com.nfd.trip4u.controller.exception.BadRequestException
import com.nfd.trip4u.controller.exception.NotFoundException
import com.nfd.trip4u.controller.util.summary
import com.nfd.trip4u.dto.UserDto
import com.nfd.trip4u.service.domain.UserService
import com.nfd.trip4u.service.exception.EntityNotFoundException
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * Author: Mary Kuchumova
 * Date: 19 Jun 2016
 * Time: 19:25
 */

@RestController
@RequestMapping(value = USER)
open class UserController {

    private val log = LogFactory.getLog(this.javaClass)

    @Autowired
    private lateinit var userService: UserService

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun returnUserInfo(@AuthenticationPrincipal username: String): UserDto? {
        try {
            return userService.findUserInfo(username)
        } catch(ex: EntityNotFoundException) {
            log.error("Can't find user.", ex)
            throw NotFoundException(ex.message, ex)
        }
    }

    @RequestMapping(method = arrayOf(RequestMethod.PATCH))
    fun updateUserInfo(@AuthenticationPrincipal username: String, @RequestBody @Valid userDto: UserDto,
                            bindingResult: BindingResult) {
        if (bindingResult.hasErrors()) throw BadRequestException(bindingResult.summary())

        try {
            userService.updateUserInfo(username, userDto)
        } catch (ex: EntityNotFoundException) {
            log.error("Can't update user info.", ex)
            throw NotFoundException(ex.message, ex)
        }
    }

    @RequestMapping(value = USER_AVATAR, method = arrayOf(RequestMethod.PATCH))
    fun updateUserAvatar(@AuthenticationPrincipal username: String, @RequestBody avatarUrl: String?) {
        try {
            userService.updateUserAvatar(username, avatarUrl)
        } catch (ex: EntityNotFoundException) {
            log.error("Can't update user avatar.", ex)
            throw NotFoundException(ex.message, ex)
        }
    }
}