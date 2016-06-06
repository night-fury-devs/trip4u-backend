package com.nfd.trip4u.service.amqp

import com.nfd.trip4u.configuration.TOPIC_NAME
import com.nfd.trip4u.entity.mailing.Email
import org.springframework.amqp.core.AmqpAdmin
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

    @Autowired
    lateinit var amqpAdmin: AmqpAdmin

    @Autowired
    lateinit var amqpTemplate: AmqpTemplate

    fun sendMail(email: Email) {
        amqpTemplate.convertAndSend(TOPIC_NAME, TOPIC_NAME, email)
    }
}