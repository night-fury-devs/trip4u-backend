package com.nfd.trip4u.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 14 June 2016
 * Time: 21:30
 */

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix="template")
open class TemplateProperties {

    //common for all templates props
    val websiteLink: String = "website"
    val contactLink: String = "contact@gmail.com"
    val username: String = "user"
    val logoImageLink: String = "logo.jpg"

    //email confirmation specific props
    val confirmationLink: String = "http://confirm.com"

    //notification specific props
    val imageLink: String = "image.jpg"
    val text: String = "notification_text"
    val accessLink: String = "http://access.com"

    //comment specific props
    val commentName: String = "username"
    val commentDate: Date = Date("01.01.1970")
    val commentText: String = "comment"

}