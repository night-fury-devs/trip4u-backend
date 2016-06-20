package com.nfd.trip4u.configuration.security

import com.nfd.trip4u.configuration.SERVER_URL
import com.nfd.trip4u.entity.domain.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.SignatureException
import org.apache.commons.logging.LogFactory
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import java.util.*

/**
 * Author: Alexey Kleschikov
 * Date: 16 Jun 2016
 * Time: 22:17
 */
class TokenGenerator {

    private val EXPIRED_TROUGH = 24 * 60 * 60 * 1000
    private val EMAIL_CLAIM = "email"
    private val ROLES = "roles"
    private val KEY = "5E3b86m26SKXhet8d9Y1UVl2p62AUoYRhLmXd6S6mh7dM0AMd6LXEC22VHSVb7hk"

    private val logger = LogFactory.getLog(this.javaClass)

    fun generateForAuthentication(authentication: Authentication): String {
        return Jwts.builder()
                .setIssuer(SERVER_URL)
                .setSubject(authentication.principal.toString())
                //TODO: Add roles to token
                .setExpiration(Date(System.currentTimeMillis() + EXPIRED_TROUGH))
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact()
    }

    fun generateForConfirmation(user: User): String {
        return Jwts.builder()
                .setIssuer(SERVER_URL)
                .setExpiration(Date(System.currentTimeMillis() + EXPIRED_TROUGH))
                .setSubject(user.userName)
                .setIssuedAt(Date())
                .claim(EMAIL_CLAIM, user.email)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact()
    }

    fun parseAuthenticationToken(token: String): Authentication {
        try {
            val claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).body

            if (claims.expiration.before(Date())) {
                throw BadCredentialsException("Token expired.")
            }

            //TODO: Add retrieving roles from token

            return UsernamePasswordAuthenticationToken(claims.subject, null)
        } catch (ex: SignatureException) {
            throw BadCredentialsException("Invalid token provided.", ex)
        }
    }

    fun parseConfirmationToken(token: String): String {
        try {
            val claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).body

            if (claims.expiration.before(Date())) {
                throw BadCredentialsException("Provided token expired.")
            }

            return claims.subject
        } catch (ex: SignatureException) {
            throw BadCredentialsException("Invalid token provided.", ex)
        }
    }
}