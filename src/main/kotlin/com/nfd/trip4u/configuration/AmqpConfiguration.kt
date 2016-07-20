package com.nfd.trip4u.configuration

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.SimpleMessageConverter
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory

/**
 * Author: Alexey Kleschikov
 * Date: 06 Jun 2016
 * Time: 23:31
 */

@Configuration
@EnableRabbit
open class AmqpConfiguration: RabbitListenerConfigurer {

    @Autowired
    private lateinit var connectionFactory: ConnectionFactory

    override fun configureRabbitListeners(registrar: RabbitListenerEndpointRegistrar?) {
        registrar?.messageHandlerMethodFactory = handlerMethodFactory()
    }

    @Bean
    open fun rabbitListenerContainerFactory(): SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory)
        factory.setConcurrentConsumers(3)
        factory.setMaxConcurrentConsumers(10)
        return factory
    }

    @Bean
    open fun handlerMethodFactory(): DefaultMessageHandlerMethodFactory {
        val factory = DefaultMessageHandlerMethodFactory()
        factory.setMessageConverter(messageConverter())
        return factory
    }

    @Bean
    open fun queue() = Queue(QUEUE_NAME)

    @Bean
    open fun exchange() = TopicExchange(TOPIC_NAME)

    @Bean
    open fun binding() = BindingBuilder.bind(queue()).to(exchange()).with(TOPIC_NAME)

    @Bean
    open fun messageConverter() = SimpleMessageConverter()
}