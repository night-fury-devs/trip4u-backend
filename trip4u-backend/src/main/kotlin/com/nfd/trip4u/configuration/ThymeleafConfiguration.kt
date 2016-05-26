package com.nfd.trip4u.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

/**
 * Author: Mary Kuchumova
 * Date: 26 May 2016
 * Time: 17:53
 */

@Configuration
open class ThymeleafConfiguration{

    @Bean
    open fun emailTemplateResolver(): ClassLoaderTemplateResolver {
        val emailTemplateResolver = ClassLoaderTemplateResolver()
        emailTemplateResolver.prefix = "/mails/"
        emailTemplateResolver.suffix = ".html"
        emailTemplateResolver.templateMode = "HTML5"
        emailTemplateResolver.characterEncoding = "UTF-8"
        emailTemplateResolver.order = 1

        return emailTemplateResolver
    }
}