package com.nfd.trip4u.configuration.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken

/**
 * Author: Alexey Kleschikov
 * Date: 16 Jun 2016
 * Time: 21:58
 */
class TokenAuthenticationProvider(val tokenService: TokenService): AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication? {
        val token = authentication?.principal

        if (token == null || (token as String).isEmpty()) {
            throw BadCredentialsException("Invalid token provided.")
        }

        if (!tokenService.contains(token)) {
            throw BadCredentialsException("Invalid token provided or token expired.")
        }

        return tokenService.retrieve(token)
    }

    override fun supports(authenticationClass: Class<*>?): Boolean {
        return PreAuthenticatedAuthenticationToken::class.equals(authenticationClass)
    }
}