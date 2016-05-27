package com.nfd.trip4u.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.thymeleaf.messageresolver.IMessageResolver
import org.thymeleaf.messageresolver.StandardMessageResolver
import org.thymeleaf.spring4.SpringTemplateEngine
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import org.thymeleaf.templateresolver.ITemplateResolver

/**
 * Author: Mary Kuchumova
 * Date: 26 May 2016
 * Time: 17:53
 */

@Configuration
@ComponentScan(basePackages = arrayOf("com.nfd.trip4u"))
open class ThymeleafConfiguration{

    @Bean
    open fun templateEngine(): SpringTemplateEngine{
        var templateEngine = SpringTemplateEngine()
        templateEngine.addTemplateResolver(emailTemplateResolver())
        templateEngine.addMessageResolver(messageResolver())
        return templateEngine
    }

    @Bean
    open fun emailTemplateResolver(): ITemplateResolver {
        val emailTemplateResolver = ClassLoaderTemplateResolver()
        emailTemplateResolver.prefix = "/mail/"
        emailTemplateResolver.suffix = ".html"
        emailTemplateResolver.templateMode = "HTML5"
        emailTemplateResolver.characterEncoding = "UTF-8"
        emailTemplateResolver.order = 1
        emailTemplateResolver.isCacheable = false
        return emailTemplateResolver
    }

    @Bean
    open fun messageResolver(): IMessageResolver {
        return StandardMessageResolver()
    }
}