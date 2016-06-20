package com.nfd.trip4u.configuration.security

import com.nfd.trip4u.service.AbstractTestCase
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken

/**
 * Author: Alexey Kleschikov
 * Date: 17 Jun 2016
 * Time: 22:43
 */

class TokenServiceTestCase: AbstractTestCase() {

    private val ROLE_COUNT = 1

    @Autowired
    private lateinit var tokenGenerator: TokenGenerator

    private lateinit var authentication: Authentication

    @Before
    fun prepareForTest() {
        this.authentication = PreAuthenticatedAuthenticationToken("some_login", "some_password",
                arrayListOf(SimpleGrantedAuthority("USER")))
    }

    @Test
    fun generateNewToken() {
        val token = tokenGenerator.generateForAuthentication(authentication)

        println("Generated token: $token")

        assertNotNull(token)
    }

    @Test
    fun parseToken() {
        val token = tokenGenerator.generateForAuthentication(authentication)

        println("Generated token: $token")

        val authentication = tokenGenerator.parseAuthenticationToken(token)

        assertNotNull(authentication)
        assertNotNull(authentication.principal)
        assertNull(authentication.credentials)
    }
}