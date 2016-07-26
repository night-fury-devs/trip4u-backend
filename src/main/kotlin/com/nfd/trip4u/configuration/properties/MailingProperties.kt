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
@ConfigurationProperties(prefix="spring.mails")
open class MailingProperties {

    var port: Int = 587
    var host: String = ""
    var username: String = ""
    var password: String = ""
    var protocol: String = "smtp"
    var auth: String = "false"
    var starttls: String = "false"
    var quitwait: String = "false"
}