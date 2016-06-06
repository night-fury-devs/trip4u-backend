package com.nfd.trip4u.configuration

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Author: Alexey Kleschikov
 * Date: 06 Jun 2016
 * Time: 23:31
 */

@Configuration
open class AmqpConfiguration {

    @Bean
    open fun queue() = Queue(QUEUE_NAME)

    @Bean
    open fun exchange() = TopicExchange(TOPIC_NAME)

    @Bean
    open fun binding() = BindingBuilder.bind(queue()).to(exchange()).with(TOPIC_NAME)
}