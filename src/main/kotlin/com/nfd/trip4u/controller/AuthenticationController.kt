package com.nfd.trip4u.controller

import com.nfd.trip4u.service.security.AuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Author: Alexey Kleschikov
 * Date: 21 Jun 2016
 * Time: 20:52
 */
@RestController
@RequestMapping(AUTH_PREFIX)
class AuthenticationController {

    @Autowired
    private lateinit var authService: AuthenticationService

    @RequestMapping(AUTH_CONFIRM)
    fun confirm(id: String): Boolean {
        return authService.confirm(id)
    }
}