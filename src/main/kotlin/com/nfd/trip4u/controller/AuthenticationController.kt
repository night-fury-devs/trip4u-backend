package com.nfd.trip4u.controller

import com.nfd.trip4u.controller.exception.BadRegistrationDataException
import com.nfd.trip4u.dto.ValidatedUser
import com.nfd.trip4u.service.security.AuthenticationService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * Author: Alexey Kleschikov
 * Date: 21 Jun 2016
 * Time: 20:52
 */
@RestController
@RequestMapping(AUTH_PREFIX)
class AuthenticationController {

    private val log = LogFactory.getLog(this.javaClass)

    @Autowired
    private lateinit var authService: AuthenticationService

    @RequestMapping(AUTH_CONFIRM)
    fun confirm(id: String): Boolean {
        return authService.confirm(id)
    }

    @RequestMapping(AUTH_REGISTER)
    fun register(@Valid @RequestBody user: ValidatedUser, bindingResult: BindingResult): Boolean {
        if (bindingResult.hasErrors()) {
            throw BadRegistrationDataException()
        }

        try {
            authService.register(user)
        } catch (ex: BadCredentialsException) {
            log.warn("Can't register user due to an error.", ex)
            throw BadRegistrationDataException()
        }

        return true
    }
}