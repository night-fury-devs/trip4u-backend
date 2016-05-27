package com.nfd.trip4u.service.mailing

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

    @Autowired
    lateinit var mailSender: JavaMailSender

    open fun sendPlainTextEmail(subject: String, recipient: String, messageBody: String) {

        val mimeMessage = mailSender.createMimeMessage()
        val message = MimeMessageHelper(mimeMessage, "UTF-8")
        message.setSubject(subject)
        message.setFrom("night.fury.devs@gmail.com")
        message.setTo(recipient)
        message.setText(messageBody);

        this.mailSender.send(mimeMessage);
    }

}
