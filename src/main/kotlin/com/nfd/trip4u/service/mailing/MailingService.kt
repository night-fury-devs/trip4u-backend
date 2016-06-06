package com.nfd.trip4u.service.mailing

import com.nfd.trip4u.entity.mailing.Email
import com.nfd.trip4u.service.thymeleaf.TemplateProducerService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

/**
 * Author: Mary Kuchumova
 * Date: 27 May 2016
 * Time: 14:49
 */

@Service
open class MailingService {

    val logger = LogFactory.getLog(this.javaClass)

    val IS_HTML_MESSAGE = true
    val DEFAULT_ENCODING = "UTF-8"

    @Autowired
    lateinit var mailSender: JavaMailSender

    @Autowired
    lateinit var templateProducer: TemplateProducerService

    fun sendMessage(email: Email) {
        try {
            val mimeMessage = mailSender.createMimeMessage()
            val message = MimeMessageHelper(mimeMessage, DEFAULT_ENCODING)
            message.setSubject(email.subject)
            message.setFrom(email.sender)
            for (recipient in email.recipients) {
                message.addTo(recipient)
            }
            if (email.template != null) {
                val template = templateProducer.produceTemplate(email.template?.parameters!!, email.template?.templateName!!)
                message.setText(template, IS_HTML_MESSAGE);
            } else {
                message.setText(email.messageBody, !IS_HTML_MESSAGE)
            }

            mailSender.send(mimeMessage)
        } catch(e: Exception) {
            logger.warn("MailingService::sendMessage: " + e.message)
        }
    }
}
