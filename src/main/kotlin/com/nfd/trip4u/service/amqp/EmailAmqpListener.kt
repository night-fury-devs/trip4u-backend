package com.nfd.trip4u.service.amqp

import com.nfd.trip4u.configuration.QUEUE_NAME
import com.nfd.trip4u.entity.mailing.Email
import com.nfd.trip4u.service.mailing.MailingService
import org.apache.commons.logging.LogFactory
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Author: Alexey Kleschikov
 * Date: 07 Jun 2016
 * Time: 21:58
 */

@Component
open class EmailAmqpListener {

    private val logger = LogFactory.getLog(this.javaClass)

    @Autowired
    private lateinit var mailingService: MailingService

    @Autowired
    private lateinit var amqpTemplate: AmqpTemplate

    @RabbitListener(queues = arrayOf(QUEUE_NAME))
    open fun processEmail(message: Email) {
//        logger.debug("Received message $message")
//        val email = ObjectInputStream(ByteArrayInputStream(message.body)).readObject() as Email
        logger.debug("Received message ${message.subject}.")

        mailingService.sendMessage(message)
    }
}