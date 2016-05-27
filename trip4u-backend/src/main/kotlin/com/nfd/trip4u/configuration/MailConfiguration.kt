package com.nfd.trip4u.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 27 May 2016
 * Time: 14:06
 */

@Configuration
@ComponentScan(basePackages = arrayOf("com.nfd.trip4u"))
open class MailConfiguration {

    val JAVA_MAIL_FILE = "javamail.yml"
    val CONFIG_PROPERTIES = "configuration.yml"

    @Bean
    open fun mailSender(): JavaMailSender {
        val properties = configProperties()

        var mailSender = JavaMailSenderImpl()
        mailSender.host = properties.getProperty("mail.server.host")
        mailSender.port = Integer.parseInt(properties.getProperty("mail.server.port"))
        mailSender.protocol = properties.getProperty("mail.server.protocol")
        mailSender.username = properties.getProperty("mail.server.username")
        mailSender.password = properties.getProperty("mail.server.password")
        mailSender.javaMailProperties = javaMailProperties()
        return mailSender
    }

    fun configProperties(): Properties {
        val properties = Properties()
        properties.load(ClassPathResource(CONFIG_PROPERTIES).inputStream)
        return properties
    }

    fun javaMailProperties(): Properties {
        val properties = Properties()
        properties.load(ClassPathResource(JAVA_MAIL_FILE).inputStream)
        return properties
    }
}