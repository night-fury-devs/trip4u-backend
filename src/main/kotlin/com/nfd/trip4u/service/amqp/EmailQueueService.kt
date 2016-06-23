package com.nfd.trip4u.service.amqp

import com.nfd.trip4u.configuration.TOPIC_NAME
import com.nfd.trip4u.entity.mailing.Email
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.converter.MessageConverter
import org.springframework.stereotype.Service

/**
 * Author: Alexey Kleschikov
 * Date: 06 Jun 2016
 * Time: 23:16
 */

@Service
open class EmailQueueService {

    @Autowired
    private lateinit var rabbitTemplate: RabbitMessagingTemplate

    @Autowired
    private lateinit var messageConverter: MessageConverter

    fun sendMessage(email: Email) {
        rabbitTemplate.messageConverter = messageConverter
        rabbitTemplate.convertAndSend(TOPIC_NAME, TOPIC_NAME, email)
    }
}