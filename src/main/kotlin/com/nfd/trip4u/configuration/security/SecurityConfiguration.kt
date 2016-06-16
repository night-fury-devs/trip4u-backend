package com.nfd.trip4u.configuration.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.http.HttpServletResponse

/**
 * Author: Alexey Kleschikov
 * Date: 14 Jun 2016
 * Time: 20:45
 */

@Configuration
@EnableWebSecurity
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true)
open class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.csrf()?.disable()
            ?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.and()
            ?.authorizeRequests()?.anyRequest()?.authenticated()?.and()
            ?.anonymous()?.disable()
            ?.exceptionHandling()?.authenticationEntryPoint(authenticationEntryPoint())?.and()
            ?.addFilterBefore(AuthenticationFilter(authenticationManager()), BasicAuthenticationFilter::class.java)
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(usernamePasswordAuthenticationProvider())
            ?.authenticationProvider(tokenAuthenticationProvider())
    }

    @Bean
    open fun authenticationEntryPoint(): AuthenticationEntryPoint
            = AuthenticationEntryPoint { request, response, exception ->
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
    }

    @Bean
    open fun tokenService(): TokenService {
        return TokenService()
    }

    @Bean
    open fun usernamePasswordAuthenticationProvider(): AuthenticationProvider {
        return UsernamePasswordAuthenticationProvider(tokenService())
    }

    @Bean
    open fun tokenAuthenticationProvider(): AuthenticationProvider {
        return TokenAuthenticationProvider(tokenService())
    }
}