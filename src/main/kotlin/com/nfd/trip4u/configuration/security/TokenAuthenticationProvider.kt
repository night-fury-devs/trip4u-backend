package com.nfd.trip4u.configuration.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Component

/**
 * Author: Alexey Kleschikov
 * Date: 16 Jun 2016
 * Time: 21:58
 */
@Component
open class TokenAuthenticationProvider: AuthenticationProvider {

    @Autowired
    private lateinit var tokenGenerator: TokenGenerator

    private val BAD_TOKEN = "Invalid token provided."

    override fun authenticate(authentication: Authentication?): Authentication? {
        val token = authentication?.principal

        if (token == null || (token as String).isEmpty()) {
            throw BadCredentialsException(BAD_TOKEN)
        }

        return tokenGenerator.parseAuthenticationToken(token)
    }

    override fun supports(authenticationClass: Class<*>?): Boolean {
        return PreAuthenticatedAuthenticationToken::class.java == authenticationClass
    }
}