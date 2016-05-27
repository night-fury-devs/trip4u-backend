package com.nfd.trip4u.service.thymeleaf

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

/**
 * Author: Mary Kuchumova
 * Date: 27 May 2016
 * Time: 11:42
 */

@Service
open class TemplateProducerService{

    @Autowired
    lateinit var templateEngine: TemplateEngine

    open fun produceTemplate(): String {
        var context = Context()
        context.setVariable("name", "Mary")

        return templateEngine.process("test-email", context)
    }

}