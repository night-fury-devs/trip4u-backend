package com.nfd.trip4u.configuration

import com.nfd.trip4u.configuration.properties.CorsProperties
import com.nfd.trip4u.configuration.security.AuthenticationFilter
import com.nfd.trip4u.configuration.security.TokenAuthenticationProvider
import com.nfd.trip4u.configuration.security.UsernamePasswordAuthenticationProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
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

    private val BCRYPT_STRENGTH = 15

    @Autowired
    private lateinit var corsProperties: CorsProperties

    @Autowired
    private lateinit var usernamePasswordAuthenticationProvider: UsernamePasswordAuthenticationProvider

    @Autowired
    private lateinit var tokenAuthenticationProvider: TokenAuthenticationProvider

    override fun configure(http: HttpSecurity?) {
        http?.csrf()?.disable()
            ?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.and()
            ?.antMatcher(REGISTER_URL)?.anonymous()?.and()
            ?.authorizeRequests()?.anyRequest()?.authenticated()?.and()
            ?.exceptionHandling()?.authenticationEntryPoint(authenticationEntryPoint())?.and()
            ?.addFilterBefore(AuthenticationFilter(authenticationManager()), BasicAuthenticationFilter::class.java)
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(usernamePasswordAuthenticationProvider)
            ?.authenticationProvider(tokenAuthenticationProvider)
    }

    @Bean
    open fun corsConfigurer(): WebMvcConfigurerAdapter {
        return object: WebMvcConfigurerAdapter() {
            override fun addCorsMappings(registry: CorsRegistry?) {
                registry?.addMapping("/**")
                        ?.allowedHeaders("*")
                        ?.allowedMethods("*")
                        ?.allowedOrigins(*corsProperties.origins)
                        ?.maxAge(3600)
                println(corsProperties.origins)
            }
        }
    }

    @Bean
    open fun authenticationEntryPoint(): AuthenticationEntryPoint
            = AuthenticationEntryPoint { request, response, exception ->
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.message)
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(BCRYPT_STRENGTH)
    }
}