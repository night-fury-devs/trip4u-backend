package com.nfd.trip4u.configuration

import com.nfd.trip4u.entity.templates.Comment
import com.nfd.trip4u.entity.templates.CommentTemplate
import com.nfd.trip4u.entity.templates.EmailConfirmationTemplate
import com.nfd.trip4u.entity.templates.NotificationTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.processor.CommentNodeProcessorMatcher
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 12 June 2016
 * Time: 16:45
 */

@Configuration
open class TemplateBeanConfiguration {

    //common for all templates props
    private val websiteLink: String = "website"
    private val contactLink: String = "contact@gmail.com"
    private val username: String = "user"
    private val logoImageLink: String = "logo.jpg"

    //email confirmation specific props
    private val confirmationLink: String = "http://confirm.com"

    //notification specific props
    private val imageLink: String = "image.jpg"
    private val text: String = "notification text"
    private val accessLink: String = "http://access.com"

    //comment specific props
    private val commentName: String = "username who commented"
    private val commentDate: Date = Date()
    private val commentText: String = "comment"

    @Bean
    open fun comment(): Comment {
        val comment = Comment(commentName, commentDate, commentText)
        return comment
    }

    @Bean
    open fun emailConfirmationTemplate(): EmailConfirmationTemplate {
        val template = EmailConfirmationTemplate(websiteLink, contactLink, username, confirmationLink,
                logoImageLink)
        return template
    }

    @Bean
    open fun notificationTemplate(): NotificationTemplate {
        val template = NotificationTemplate(websiteLink, contactLink, username, logoImageLink, imageLink,
                text, accessLink)
        return template
    }

    @Bean
    open fun commentNotificationTemplate(): CommentTemplate {
        val template = CommentTemplate(websiteLink, contactLink, username, logoImageLink, imageLink,
                text, accessLink, comment())
        return template
    }
}