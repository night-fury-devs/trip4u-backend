package com.nfd.trip4u.thymeleaf

import com.nfd.trip4u.Application
import com.nfd.trip4u.configuration.ThymeleafConfiguration
import com.nfd.trip4u.configuration.properties.TemplateProperties
import com.nfd.trip4u.entity.templates.CommentTemplate
import com.nfd.trip4u.entity.templates.EmailConfirmationTemplate
import com.nfd.trip4u.entity.templates.NotificationTemplate
import com.nfd.trip4u.service.thymeleaf.TemplateProducerService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 27 May 2016
 * Time: 12:09
 */

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(Application::class, ThymeleafConfiguration::class))
class TestTemplateGeneration {

    val EXISTING_TEMPLATE_NAME = "test-email"
    val NOT_EXISTING_TEMPLATE_NAME = "this-template-name-doesnt-exist-in-resource-folder-18572927862923"
    val TEST_KEY = "message"
    val TEST_VALUE = "hello in trip4u"

    val EMAIL_CONFIRMATION_TEMPLATE_NAME = "registrationConfirmation"
    val COMMENT_NOTIFICATION_TEMPLATE_NAME = "newCommentNotification"
    val NOTIFICATION_TEMPLATE_NAME = "notification"

    private var parameters = HashMap<String, String>()

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
    fun testPassEmptyParameterMapToTemplate() {
        parameters.clear()
        val templateResult = templateProducer.produceTemplate(parameters, EXISTING_TEMPLATE_NAME)
        Assert.assertNotNull(templateResult)
        Assert.assertEquals(templateResult?.indexOf(TEST_VALUE), -1)
    }

    @Test
    fun testNotExistingTemplate() {
        parameters.clear()
        val templateResult = templateProducer.produceTemplate(parameters, NOT_EXISTING_TEMPLATE_NAME)
        Assert.assertNull(templateResult)
    }

    @Test
    fun testExistingTemplateGeneration(){
        parameters.put(TEST_KEY, TEST_VALUE)
        val templateResult = templateProducer.produceTemplate(parameters, EXISTING_TEMPLATE_NAME)
        Assert.assertNotNull(templateResult)
        Assert.assertFalse(templateResult?.indexOf(TEST_VALUE)!!.equals(-1))
    }

    @Test
    fun testEmailConfirmationTemplateGeneration(){
        val templateResult = templateProducer.produceTemplate(emailConfirmationTemplate,
                EMAIL_CONFIRMATION_TEMPLATE_NAME)
        Assert.assertNotNull(templateResult)
        println(templateResult)
        Assert.assertTrue(!templateResult?.indexOf(emailConfirmationTemplate.websiteLink)!!.equals(-1))
        Assert.assertTrue(!templateResult?.indexOf(emailConfirmationTemplate.contactLink)!!.equals(-1))
        Assert.assertTrue(!templateResult?.indexOf(emailConfirmationTemplate.username)!!.equals(-1))
        Assert.assertTrue(!templateResult?.indexOf(emailConfirmationTemplate.logoImageLink)!!.equals(-1))
        Assert.assertTrue(!templateResult?.indexOf(emailConfirmationTemplate.confirmationLink)!!.equals(-1))

    }

    @Test
    fun testNotificationTemplateGeneration(){
        val templateResult = templateProducer.produceTemplate(notificationTemplate,
                NOTIFICATION_TEMPLATE_NAME)
        Assert.assertNotNull(templateResult)
        Assert.assertTrue(!templateResult?.indexOf(notificationTemplate.websiteLink)!!.equals(-1))
        Assert.assertTrue(!templateResult?.indexOf(notificationTemplate.contactLink)!!.equals(-1))
        Assert.assertTrue(!templateResult?.indexOf(notificationTemplate.username)!!.equals(-1))
        Assert.assertTrue(!templateResult?.indexOf(notificationTemplate.logoImageLink)!!.equals(-1))
        Assert.assertTrue(!templateResult?.indexOf(notificationTemplate.accessLink)!!.equals(-1))
        Assert.assertTrue(!templateResult?.indexOf(notificationTemplate.imageLink)!!.equals(-1))
        Assert.assertTrue(!templateResult?.indexOf(notificationTemplate.text)!!.equals(-1))
    }

    @Test
    fun testCommentNotificationTemplateGeneration(){
        val templateResult = templateProducer.produceTemplate(commentNotificationTemplate,
                COMMENT_NOTIFICATION_TEMPLATE_NAME)
        Assert.assertNotNull(templateResult)
        Assert.assertTrue(!templateResult?.indexOf(commentNotificationTemplate.websiteLink)!!.equals(-1))
        Assert.assertTrue(!templateResult?.indexOf(commentNotificationTemplate.contactLink)!!.equals(-1))
        Assert.assertTrue(!templateResult?.indexOf(commentNotificationTemplate.username)!!.equals(-1))
        Assert.assertTrue(!templateResult?.indexOf(commentNotificationTemplate.logoImageLink)!!.equals(-1))
        Assert.assertTrue(!templateResult?.indexOf(commentNotificationTemplate.accessLink)!!.equals(-1))
        Assert.assertTrue(!templateResult?.indexOf(commentNotificationTemplate.text)!!.equals(-1))
        Assert.assertTrue(!templateResult?.indexOf(commentNotificationTemplate.comment.name)!!.equals(-1))
        Assert.assertTrue(!templateResult?.indexOf(commentNotificationTemplate.comment.text)!!.equals(-1))

    }
}
