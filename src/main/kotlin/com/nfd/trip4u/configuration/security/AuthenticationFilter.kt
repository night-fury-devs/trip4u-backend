package com.nfd.trip4u.configuration.security

import org.apache.commons.logging.LogFactory
import org.slf4j.MDC
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import org.springframework.web.util.UrlPathHelper
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

class AuthenticationFilter: GenericFilterBean() {

    private val USERNAME_HEADER = "X-Auth-Username"
    private val PASSWORD_HEADER = "X-Auth-Password"
    private val TOKEN_HEADER = "X-Auth-Token"

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse

        val username = httpRequest.getHeader(USERNAME_HEADER)
        val password = httpRequest.getHeader(PASSWORD_HEADER)
        val token = httpRequest.getHeader(TOKEN_HEADER)

        val resourcePath = UrlPathHelper().getPathWithinApplication(httpRequest)

        //TODO: Review code in try block to change its executing order.
        try {
            if (postToAuthenticate(httpRequest, resourcePath)) {
                logger.debug("Trying to authenticate user $username by X-Auth-Username method.")

                processUsernamePasswordAuthentication(httpResponse, username, password)
                return
            }

            if (token != null) {
                logger.debug("Trying to authenticate user by X-Auth-Token method (Token: $token).")

                processTokenAuthentication()
            }

            logger.debug("Passing request down the filter chain.")

            addSessionContextToLogging()
            chain?.doFilter(request, response)
        } catch (ex: InternalAuthenticationServiceException) {
            SecurityContextHolder.clearContext()
            logger.error("Internal Authentication error occurred.", ex)
            httpResponse.sendError((HttpServletResponse.SC_INTERNAL_SERVER_ERROR))
        } catch (ex: AuthenticationException) {
            SecurityContextHolder.clearContext()
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.message)
        } finally {
            MDC.remove(TOKEN_SESSION_KEY)
            MDC.remove(USER_SESSION_KEY)
        }
        //TODO: https://www.future-processing.pl/blog/exploring-spring-boot-and-spring-security-custom-token-based-authentication-of-rest-services-with-spring-security-and-pinch-of-spring-java-configuration-and-spring-integration-testing/
    }
}