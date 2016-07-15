package com.nfd.trip4u.controller

import com.nfd.trip4u.controller.exception.BadRegistrationDataException
import com.nfd.trip4u.dto.RegistrationDataDto
import com.nfd.trip4u.service.security.AuthenticationService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
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

    @RequestMapping(value = AUTH_CONFIRM, method = arrayOf(RequestMethod.PUT))
    fun confirm(id: String): Boolean {
        return authService.confirm(id)
    }

    @RequestMapping(value = AUTH_REGISTER, method = arrayOf(RequestMethod.POST))
    fun register(@Valid @RequestBody registrationDataDto: RegistrationDataDto, bindingResult: BindingResult): Boolean {
        if (bindingResult.hasErrors()) {
            throw BadRegistrationDataException()
        }

        try {
            authService.register(registrationDataDto)
        } catch (ex: BadCredentialsException) {
            log.warn("Can't register user due to an error.", ex)
            throw BadRegistrationDataException()
        }

        return true
    }
}