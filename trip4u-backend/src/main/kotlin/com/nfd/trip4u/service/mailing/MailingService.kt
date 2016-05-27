package com.nfd.trip4u.service.mailing

import com.nfd.trip4u.service.thymeleaf.TemplateProducerService
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

    val IS_HTML_MESSAGE = true

    @Autowired
    lateinit var mailSender: JavaMailSender

    @Autowired
    lateinit var templateProducer: TemplateProducerService

    open fun sendPlainTextEmail(subject: String, recipient: String, messageBody: String) {

        val mimeMessage = mailSender.createMimeMessage()
        val message = MimeMessageHelper(mimeMessage, "UTF-8")
        message.setSubject(subject)
        message.setFrom("night.fury.devs@gmail.com")
        message.setTo(recipient)
        message.setText(messageBody);

        this.mailSender.send(mimeMessage);
    }

    open fun sendTemplateEmail(subject: String, recipient: String, templateParameters: Map<String, String>, templateName: String){
        val mimeMessage = mailSender.createMimeMessage()
        val message = MimeMessageHelper(mimeMessage, "UTF-8")
        message.setSubject(subject)
        message.setFrom("night.fury.devs@gmail.com")
        message.setTo(recipient)

        var template = templateProducer.produceTemplate(templateParameters, templateName)

        message.setText(template, IS_HTML_MESSAGE);

        this.mailSender.send(mimeMessage);
    }
}
