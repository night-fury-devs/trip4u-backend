package com.nfd.trip4u.configuration.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.nfd.trip4u.configuration.AUTHENTICATE_URL
import com.nfd.trip4u.configuration.HTTP_POST
import org.apache.commons.logging.LogFactory
import org.slf4j.MDC
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.web.filter.GenericFilterBean
import org.springframework.web.util.UrlPathHelper
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Author: Alexey Kleschikov
 * Date: 14 Jun 2016
 * Time: 23:02
 */

class AuthenticationFilter() : GenericFilterBean() {

    private val USERNAME_PARAMETER = "username"
    private val PASSWORD_PARAMETER = "password"
    private val TOKEN_HEADER = "X-Auth-Token"

    private val TOKEN_SESSION_KEY = "token_session_key"
    private val USER_SESSION_KEY = "user_session_key"

    private val log = LogFactory.getLog(this.javaClass)

    private var authenticationManager: AuthenticationManager? = null

    constructor(authenticationManager: AuthenticationManager) : this() {
        this.authenticationManager = authenticationManager
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse

        val username = httpRequest.getParameter(USERNAME_PARAMETER)
        val password = httpRequest.getParameter(PASSWORD_PARAMETER)
        val token = httpRequest.getHeader(TOKEN_HEADER)

        val resourcePath = UrlPathHelper().getPathWithinApplication(httpRequest)

        try {
            if (postToAuthenticate(httpRequest, resourcePath)) {
                log.debug("Trying to authenticate user $username by X-Auth-Username method.")

                processUsernamePasswordAuthentication(httpResponse, username, password)
                return
            }

            if (token != null) {
                log.debug("Trying to authenticate user by X-Auth-Token method (Token: $token).")

                processTokenAuthentication(token)
            }

            log.debug("Passing request down the filter chain.")

//            addSessionContextToLogging()
            chain?.doFilter(request, response)
        } catch (ex: InternalAuthenticationServiceException) {
            SecurityContextHolder.clearContext()
            log.error("Internal Authentication error occurred.", ex)
            httpResponse.sendError((HttpServletResponse.SC_INTERNAL_SERVER_ERROR))
        } catch (ex: AuthenticationException) {
            SecurityContextHolder.clearContext()
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.message)
        } finally {
            MDC.remove(TOKEN_SESSION_KEY)
            MDC.remove(USER_SESSION_KEY)
        }
    }

    private fun postToAuthenticate(request: HttpServletRequest, path: String): Boolean {
        return AUTHENTICATE_URL.equals(path, true) && HTTP_POST.equals(request.method, true)
    }

    private fun addSessionContextToLogging() {
        val authentication = SecurityContextHolder.getContext().authentication
        var tokenValue: String? = null

        if (authentication != null && !authentication.details.toString().isNullOrEmpty()) {
            val encoder = MessageDigestPasswordEncoder("SHA-1")
            tokenValue = encoder.encodePassword(authentication.details.toString(), Random().nextDouble())
        }
        MDC.put(TOKEN_SESSION_KEY, tokenValue)

        var userValue: String? = null
        if (authentication != null && !authentication.principal.toString().isNullOrEmpty()) {
            userValue = authentication.principal.toString()
        }
        MDC.put(USER_SESSION_KEY, userValue)
    }

    private fun processUsernamePasswordAuthentication(response: HttpServletResponse, username: String?, password:
            String?) {
        val resultOfAuthentication = tryToAuthenticateWithUsernameAndPassword(username, password)
        SecurityContextHolder.getContext().authentication = resultOfAuthentication

        response.status = HttpServletResponse.SC_OK

        val tokenResponse = TokenResponse(resultOfAuthentication.principal.toString())
        val tokenJsonResponse = ObjectMapper().writeValueAsString(tokenResponse)

        response.addHeader("Content-Type", "application/json")
        response.writer.print(tokenJsonResponse)
    }

    private fun tryToAuthenticateWithUsernameAndPassword(username: String?, password: String?)
            = tryToAuthenticate(UsernamePasswordAuthenticationToken(username, password))

    private fun processTokenAuthentication(token: String?) {
        val resultOfAuthentication = tryToAuthenticateWithToken(token)

        SecurityContextHolder.getContext().authentication = resultOfAuthentication
    }

    private fun tryToAuthenticateWithToken(token: String?)
            = tryToAuthenticate(PreAuthenticatedAuthenticationToken(token, null))

    private fun tryToAuthenticate(requestAuthentication: Authentication): Authentication {
        val responseAuthentication = authenticationManager?.authenticate(requestAuthentication)

        if (responseAuthentication == null || !responseAuthentication.isAuthenticated) {
            throw InternalAuthenticationServiceException("Unable to authenticate user for provided credentials")
        }

        log.debug("User successfully authenticated.")

        return responseAuthentication
    }
}

private data class TokenResponse(val token: String)