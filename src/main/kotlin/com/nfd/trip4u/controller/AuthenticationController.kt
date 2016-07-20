package com.nfd.trip4u.controller

import com.nfd.trip4u.controller.exception.BadRequestException
import com.nfd.trip4u.controller.util.summary
import com.nfd.trip4u.dto.RegistrationDataDto
import com.nfd.trip4u.service.security.AuthenticationService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * Author: Alexey Kleschikov
 * Date: 21 Jun 2016
 * Time: 20:52
 */
@RestController
@RequestMapping(AUTH)
open class AuthenticationController {

    private val log = LogFactory.getLog(this.javaClass)

    @Autowired
    private lateinit var authService: AuthenticationService

    @RequestMapping(value = AUTH_CONFIRM, method = arrayOf(RequestMethod.PUT))
    fun confirm(id: String): Boolean {
        return authService.confirm(id)
    }

    @RequestMapping(value = AUTH_REGISTER, method = arrayOf(RequestMethod.POST))
    fun register(@Valid @RequestBody registrationDataDto: RegistrationDataDto, bindingResult: BindingResult): Boolean {
        if (bindingResult.hasErrors()) throw BadRequestException(bindingResult.summary())

        try {
            authService.register(registrationDataDto)
        } catch (ex: BadCredentialsException) {
            log.warn("Can't register user due to an error.", ex)
            throw BadRequestException(ex.message, ex)
        }

        return true
    }

    @RequestMapping(value = AUTH_CHECK_USERNAME, method = arrayOf(RequestMethod.GET))
    fun isUsernameFree(@RequestParam(name = "value", required = true) username: String): Boolean {
        return authService.isUsernameFree(username)
    }

    @RequestMapping(value = AUTH_CHECK_EMAIL, method = arrayOf(RequestMethod.GET))
    fun isEmailFree(@RequestParam(name = "value", required = true) email: String): Boolean {
        return authService.isEmailFree(email)
    }
}