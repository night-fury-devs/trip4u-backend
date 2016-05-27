package com.nfd.trip4u.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component

/**
 * Author: Mary Kuchumova
 * Date: 27 May 2016
 * Time: 15:14
 */

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix="spring.mail")
class MailingProperties {

    lateinit var port: String

    lateinit var host: String

    lateinit var username: String

    lateinit var password: String

    lateinit var protocol: String

    lateinit var auth: String

    lateinit var starttls: String

    lateinit var quitwait: String
}