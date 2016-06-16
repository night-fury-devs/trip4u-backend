package com.nfd.trip4u.configuration.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

/**
 * Author: Alexey Kleschikov
 * Date: 16 Jun 2016
 * Time: 21:20
 */
class UsernamePasswordAuthenticationProvider(val tokenService: TokenService): AuthenticationProvider {

    override fun authenticate(authentication: Authentication?): Authentication? {
        val username = authentication?.principal
        val password = authentication?.credentials

        if (username == null || password == null) {
            throw BadCredentialsException("Invalid user credentials.")
        }

        //TODO: Call AuthService to find username and to compare stored password with provided
        //TODO: Change next 4 lines according to app logic and token generation
//        val resultOfAuthentication = externalServiceAuthenticator.authenticate(username, password)
//        val token = tokenService.generateNewToken()
//        resultOfAuthentication.token = token
//        tokenService.store(token, resultOfAuthentication)

        //TODO: Return an authentication object
        return null
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return UsernamePasswordAuthenticationToken::class.equals(authentication)
    }
}