package com.nfd.trip4u.service.thymeleaf

import com.nfd.trip4u.AbstractTestCase
import com.nfd.trip4u.configuration.properties.TemplateProperties
import com.nfd.trip4u.entity.templates.CommentTemplate
import com.nfd.trip4u.entity.templates.EmailConfirmationTemplate
import com.nfd.trip4u.entity.templates.NotificationTemplate
import org.junit.Assert
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

/**
 * Author: Mary Kuchumova
 * Date: 27 May 2016
 * Time: 12:09
 */
@Component
open class TemplateGenerationTestCase : AbstractTestCase() {

    val EXISTING_TEMPLATE_NAME = "test-email"
    val NOT_EXISTING_TEMPLATE_NAME = "this-template-name-doesnt-exist-in-resource-folder-18572927862923"
    val TEST_KEY = "message"
    val TEST_VALUE = "hello in trip4u"

    val EMAIL_CONFIRMATION_TEMPLATE_NAME = "registrationConfirmation"
    val COMMENT_NOTIFICATION_TEMPLATE_NAME = "newCommentNotification"
    val NOTIFICATION_TEMPLATE_NAME = "notification"

    @Autowired
    @Qualifier("emailConfirmationTemplate")
    lateinit var emailConfirmationTemplate: EmailConfirmationTemplate

    @Autowired
    @Qualifier("notificationTemplate")
    private lateinit var notificationTemplate: NotificationTemplate

    @Autowired
    @Qualifier("commentNotificationTemplate")
    private lateinit var commentNotificationTemplate: CommentTemplate

    @Autowired
    private lateinit var templateProducer: TemplateProducerService

    @Autowired
    private lateinit var templateProperties: TemplateProperties

    @Test
    fun generateNotExistingTemplate() {
        val templateResult = templateProducer.produceTemplate(emailConfirmationTemplate, NOT_EXISTING_TEMPLATE_NAME)
        Assert.assertNull(templateResult)
    }

    @Test
    fun generateEmailConfirmationTemplate(){
        val templateResult = templateProducer.produceTemplate(emailConfirmationTemplate,
                EMAIL_CONFIRMATION_TEMPLATE_NAME)
        Assert.assertNotNull(templateResult)
        val template = templateResult
        if(template != null) {
            Assert.assertTrue(!template.indexOf(emailConfirmationTemplate.websiteLink).equals(-1))
            Assert.assertTrue(!template.indexOf(emailConfirmationTemplate.contactLink).equals(-1))
            Assert.assertTrue(!template.indexOf(emailConfirmationTemplate.username).equals(-1))
            Assert.assertTrue(!template.indexOf(emailConfirmationTemplate.logoImageLink).equals(-1))
            Assert.assertTrue(!template.indexOf(emailConfirmationTemplate.confirmationLink).equals(-1))
        }
    }

    @Test
    fun generateNotificationTemplate(){
        val templateResult = templateProducer.produceTemplate(notificationTemplate,
                NOTIFICATION_TEMPLATE_NAME)
        Assert.assertNotNull(templateResult)
        val template = templateResult
        if(template != null) {
            Assert.assertTrue(!template.indexOf(notificationTemplate.websiteLink).equals(-1))
            Assert.assertTrue(!template.indexOf(notificationTemplate.contactLink).equals(-1))
            Assert.assertTrue(!template.indexOf(notificationTemplate.username).equals(-1))
            Assert.assertTrue(!template.indexOf(notificationTemplate.logoImageLink).equals(-1))
            Assert.assertTrue(!template.indexOf(notificationTemplate.accessLink).equals(-1))
            Assert.assertTrue(!template.indexOf(notificationTemplate.imageLink).equals(-1))
            Assert.assertTrue(!template.indexOf(notificationTemplate.text).equals(-1))
        }
    }

    @Test
    fun generateCommentNotificationTemplate(){
        val templateResult = templateProducer.produceTemplate(commentNotificationTemplate,
                COMMENT_NOTIFICATION_TEMPLATE_NAME)
        Assert.assertNotNull(templateResult)
        val template = templateResult
        if(template != null) {
            Assert.assertTrue(!template.indexOf(commentNotificationTemplate.websiteLink).equals(-1))
            Assert.assertTrue(!template.indexOf(commentNotificationTemplate.contactLink).equals(-1))
            Assert.assertTrue(!template.indexOf(commentNotificationTemplate.username).equals(-1))
            Assert.assertTrue(!template.indexOf(commentNotificationTemplate.logoImageLink).equals(-1))
            Assert.assertTrue(!template.indexOf(commentNotificationTemplate.accessLink).equals(-1))
            Assert.assertTrue(!template.indexOf(commentNotificationTemplate.text).equals(-1))
            Assert.assertTrue(!template.indexOf(commentNotificationTemplate.comment.name).equals(-1))
            Assert.assertTrue(!template.indexOf(commentNotificationTemplate.comment.text).equals(-1))
        }
    }
}
