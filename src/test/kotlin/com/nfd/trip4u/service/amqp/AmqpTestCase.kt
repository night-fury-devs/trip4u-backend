package com.nfd.trip4u.service.amqp

import com.nfd.trip4u.entity.mailing.Email
import com.nfd.trip4u.service.AbstractTestCase
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * Author: Alexey Kleschikov
 * Date: 07 Jun 2016
 * Time: 22:11
 */

class AmqpTestCase: AbstractTestCase() {

    @Autowired
    private lateinit var amqpProducer: EmailAmqpProducer

    @Test
    fun testSendingMessage() {
        val email = Email("alex.vesy96@gmail.com", arrayListOf("forevest96@gmail.com"), "TEST EMAIL", null, null)

        amqpProducer.sendMessage(email)
    }
}