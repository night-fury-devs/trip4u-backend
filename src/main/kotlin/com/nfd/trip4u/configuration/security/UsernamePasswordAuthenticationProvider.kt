package com.nfd.trip4u.configuration.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Component

/**
 * Author: Alexey Kleschikov
 * Date: 16 Jun 2016
 * Time: 21:20
 */
@Component
open class UsernamePasswordAuthenticationProvider: AuthenticationProvider {

    @Autowired
    private lateinit var tokenService: TokenService

    @Autowired
    private lateinit var authService: AuthenticationService

    override fun authenticate(authentication: Authentication?): Authentication? {
        val username = authentication?.principal?.toString()
        val password = authentication?.credentials?.toString()

        if (username == null || password == null) {
            throw BadCredentialsException("Invalid user credentials.")
        }

        val auth = authService.login(username, password)
        val token = tokenService.generateTokenFor(auth)

        return PreAuthenticatedAuthenticationToken(token, null)
    }

    override fun supports(authenticationClass: Class<*>?): Boolean {
        return UsernamePasswordAuthenticationToken::class.java == authenticationClass
    }
}