package com.nfd.trip4u.configuration.security

import org.springframework.security.core.Authentication

/**
 * Author: Alexey Kleschikov
 * Date: 16 Jun 2016
 * Time: 22:17
 */
class TokenService {

    //TODO: Implement necessary methods.
    fun generateNewToken(): String {
        throw UnsupportedOperationException()
    }

    fun store(token: String, authentication: Authentication) {
        throw UnsupportedOperationException()
    }

    fun contains(token: String): Boolean {
        throw UnsupportedOperationException()
    }

    fun retrieve(token: String): Authentication {
        throw UnsupportedOperationException()
    }
}