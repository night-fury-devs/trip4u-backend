package com.nfd.trip4u.service.amqp

import com.nfd.trip4u.configuration.TOPIC_NAME
import com.nfd.trip4u.entity.mailing.Email
import org.apache.commons.logging.LogFactory
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Author: Alexey Kleschikov
 * Date: 06 Jun 2016
 * Time: 23:16
 */

@Service
open class EmailAmqpProducer {

    private val logger = LogFactory.getLog(this.javaClass)

    @Autowired
    private lateinit var amqpTemplate: AmqpTemplate

    fun sendMessage(email: Email) {
        logger.debug("Send message $email")

        amqpTemplate.convertAndSend(TOPIC_NAME, TOPIC_NAME, email)
    }
}