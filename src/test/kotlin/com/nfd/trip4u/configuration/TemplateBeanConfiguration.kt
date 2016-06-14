package com.nfd.trip4u.configuration

import com.nfd.trip4u.configuration.properties.TemplateProperties
import com.nfd.trip4u.entity.templates.Comment
import com.nfd.trip4u.entity.templates.CommentTemplate
import com.nfd.trip4u.entity.templates.EmailConfirmationTemplate
import com.nfd.trip4u.entity.templates.NotificationTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.thymeleaf.processor.CommentNodeProcessorMatcher
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 12 June 2016
 * Time: 16:45
 */

@Configuration
open class TemplateBeanConfiguration {

    @Autowired
    private lateinit var templateProperties: TemplateProperties

    @Bean
    open fun comment(): Comment {
        val comment = Comment(templateProperties.commentName, templateProperties.commentDate,
                templateProperties.commentText)
        return comment
    }

    @Bean
    open fun emailConfirmationTemplate(): EmailConfirmationTemplate {
        val template = EmailConfirmationTemplate(templateProperties.websiteLink,
                templateProperties.contactLink, templateProperties.username,
                templateProperties.confirmationLink, templateProperties.logoImageLink)
        return template
    }

    @Bean
    open fun notificationTemplate(): NotificationTemplate {
        val template = NotificationTemplate(templateProperties.websiteLink,
                templateProperties.contactLink, templateProperties.username,
                templateProperties.logoImageLink, templateProperties.imageLink,
                templateProperties.text, templateProperties.accessLink)
        return template
    }

    @Bean
    open fun commentNotificationTemplate(): CommentTemplate {
        val template = CommentTemplate(templateProperties.websiteLink,
                templateProperties.contactLink, templateProperties.username,
                templateProperties.logoImageLink, templateProperties.imageLink,
                templateProperties.text, templateProperties.accessLink, comment())
        return template
    }
}