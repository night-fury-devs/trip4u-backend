package com.nfd.trip4u.configuration

import com.nfd.trip4u.configuration.properties.MailingProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 27 May 2016
 * Time: 14:06
 */

@Configuration
open class MailConfiguration {

    @Autowired
    lateinit var mailingProperties: MailingProperties

    @Bean
    open fun mailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = mailingProperties.host
        mailSender.port = mailingProperties.port
        mailSender.protocol = mailingProperties.protocol
        mailSender.username = mailingProperties.username
        mailSender.password = mailingProperties.password
        mailSender.javaMailProperties = javaMailProperties()
        return mailSender
    }

    fun javaMailProperties(): Properties {
        val properties = Properties()
        properties.setProperty("mail.smtp.auth", mailingProperties.auth)
        properties.setProperty("mail.smtp.starttls.enable", mailingProperties.starttls)
        properties.setProperty("mail.smtp.quitwait", mailingProperties.quitwait)
        return properties
    }
}