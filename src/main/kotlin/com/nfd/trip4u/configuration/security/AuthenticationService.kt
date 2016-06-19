package com.nfd.trip4u.configuration.security

import com.nfd.trip4u.service.domain.UserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * Author: Alexey Kleschikov
 * Date: 19 Jun 2016
 * Time: 13:25
 */
@Service
open class AuthenticationService {

    @Autowired
    private lateinit var userService: UserServiceImpl

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    fun login(username: String, password: String): Authentication {
        val user = userService.findByUserName(username)

        return if (passwordEncoder.matches(password, user?.password)) {
            UsernamePasswordAuthenticationToken(username, null)
        } else {
            throw BadCredentialsException("Username and password are not match.")
        }
    }
}