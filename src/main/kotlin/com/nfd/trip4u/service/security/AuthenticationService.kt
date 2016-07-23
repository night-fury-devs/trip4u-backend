package com.nfd.trip4u.service.security

import com.nfd.trip4u.configuration.HOST
import com.nfd.trip4u.configuration.properties.MailingProperties
import com.nfd.trip4u.configuration.security.TokenGenerator
import com.nfd.trip4u.dto.RegistrationDataDto
import com.nfd.trip4u.entity.domain.User
import com.nfd.trip4u.entity.mailing.Email
import com.nfd.trip4u.entity.mailing.TemplateWrapper
import com.nfd.trip4u.entity.templates.EmailConfirmationTemplate
import com.nfd.trip4u.service.amqp.EmailQueueService
import com.nfd.trip4u.service.domain.UserService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * Author: Alexey Kleschikov
 * Date: 19 Jun 2016
 * Time: 13:25
 */
@Service
open class AuthenticationService {

    private val log = LogFactory.getLog(this.javaClass)

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var tokenGenerator: TokenGenerator

    @Autowired
    private lateinit var emailQueueService: EmailQueueService

    @Autowired
    private lateinit var mailingProperties: MailingProperties

    fun login(username: String, password: String): Authentication {
        val user = userService.findByUsername(username)

        return if (passwordEncoder.matches(password, user?.password)) {
            UsernamePasswordAuthenticationToken(username, null)
        } else {
            throw BadCredentialsException("Username and password are not match.")
        }
    }

    fun register(registrationDataDto: RegistrationDataDto) {
        if (userService.exists(registrationDataDto)) {
            throw BadCredentialsException("User with provided username or email already exists.")
        }

//        val domainUser = User(null, registrationDataDto.username, registrationDataDto.email,
//                passwordEncoder.encode(registrationDataDto.password), null, null, registrationDataDto.lastName,
//                registrationDataDto.firstName, null, Gender.NOT_DEFINED, null, arrayListOf(Role.USER), false)
        val domainUser = User(registrationDataDto.username, registrationDataDto.email, passwordEncoder.encode(registrationDataDto.password))
        domainUser.lastName = registrationDataDto.lastName
        domainUser.firstName = registrationDataDto.firstName

        userService.save(domainUser)

        sendConfirmationEmail(domainUser)
    }

    fun confirm(encodedToken: String): Boolean {
        try {
            val username = tokenGenerator.parseConfirmationToken(encodedToken)

            val user = userService.findByUsername(username) ?: throw UsernameNotFoundException("Username not found.")
            user.activated = true
            userService.save(user)
        } catch (ex: BadCredentialsException) {
            log.error("Unable to confirm registration.", ex)
            return false
        }

        return true
    }

    fun sendConfirmationEmail(user: User) {
        val token = tokenGenerator.generateForConfirmation(user)

        val url = "$HOST/auth/confirm?id=$token"

        val template = EmailConfirmationTemplate(HOST, "mailto:${mailingProperties.username}", user.username, url, "")
        val templateWrapper = TemplateWrapper("registrationConfirmation", template)
        val email = Email(mailingProperties.username, arrayListOf(user.email), "Trip4U registration confirmation.", null,
                templateWrapper)

        emailQueueService.sendMessage(email)
    }

    fun isUsernameFree(username: String): Boolean {
        return userService.findByUsername(username) == null
    }

    fun isEmailFree(email: String): Boolean {
        return userService.findByEmail(email) == null
    }
}