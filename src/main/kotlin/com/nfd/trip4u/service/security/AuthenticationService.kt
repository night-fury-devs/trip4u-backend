package com.nfd.trip4u.service.security

import com.nfd.trip4u.configuration.HOST
import com.nfd.trip4u.configuration.properties.MailingProperties
import com.nfd.trip4u.configuration.security.TokenGenerator
import com.nfd.trip4u.dto.UserDto
import com.nfd.trip4u.entity.domain.Gender
import com.nfd.trip4u.entity.domain.Role
import com.nfd.trip4u.entity.domain.User
import com.nfd.trip4u.entity.mailing.Email
import com.nfd.trip4u.entity.mailing.TemplateWrapper
import com.nfd.trip4u.entity.templates.EmailConfirmationTemplate
import com.nfd.trip4u.service.amqp.EmailQueueService
import com.nfd.trip4u.service.domain.UserService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.nio.charset.Charset
import java.util.*

/**
 * Author: Alexey Kleschikov
 * Date: 19 Jun 2016
 * Time: 13:25
 */
@Service
open class AuthenticationService {

    private val UTF8 = "UTF-8"
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
        val user = userService.findByUserName(username)

        return if (passwordEncoder.matches(password, user?.password)) {
            UsernamePasswordAuthenticationToken(username, null)
        } else {
            throw BadCredentialsException("Username and password are not match.")
        }
    }

    fun register(userDto: UserDto) {
        if (userService.exists(userDto)) {
            throw BadCredentialsException("User with provided username or email already exists.")
        }

        val domainUser = User(null, userDto.userName, userDto.email, passwordEncoder.encode(userDto.password), null,
                userDto.lastName, userDto.firstName, null, Gender.NOT_DEFINED, null,
                arrayListOf(Role.USER), false)

        userService.save(domainUser)

//        sendConfirmationEmail(domainUser)
    }

    fun confirm(encodedToken: String): Boolean {
        val token = String(Base64.getDecoder().decode(encodedToken), Charset.forName(UTF8))

        try {
            val username = tokenGenerator.parseConfirmationToken(token)

            val user = userService.findByUserName(username) ?: throw UsernameNotFoundException("Username not found.")
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
        val encodedToken = Base64.getEncoder().encodeToString(token.toByteArray(charset = Charset.forName(UTF8)))

        val url = "$HOST/auth/confirm?id=$encodedToken"

        val template = EmailConfirmationTemplate(HOST, "mailto:${mailingProperties.username}", user.userName, url, "")
        val templateWrapper = TemplateWrapper("registrationConfirmation", template)
        val email = Email(mailingProperties.username, arrayListOf(user.email), "Trip4U registration confirmation.", null,
                templateWrapper)

        emailQueueService.sendMessage(email)
    }
}