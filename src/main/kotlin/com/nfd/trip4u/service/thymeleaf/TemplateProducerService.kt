package com.nfd.trip4u.service.thymeleaf

import com.nfd.trip4u.entity.templates.BaseTemplate
import org.apache.commons.logging.LogFactory
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

    private val log = LogFactory.getLog(this.javaClass)

    private val TEMPLATE_ERROR = "Error while producing a template."
    private val TEMPLATE_ENTITY_KEY = "content"

    @Autowired
    lateinit var templateEngine: TemplateEngine

    open fun produceTemplate(template: BaseTemplate, templateName: String): String? {
        val context = Context()
        context.setVariable(TEMPLATE_ENTITY_KEY, template)

        return processTemplate(context, templateName)
    }

    private fun processTemplate(context: Context, templateName: String): String? {
        try{
            return templateEngine.process(templateName, context)
        } catch(e: Exception) {
            log.warn(TEMPLATE_ERROR, e)
            return null
        }
    }

}