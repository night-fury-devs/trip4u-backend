package com.nfd.trip4u.mailing

import com.nfd.trip4u.Application
import com.nfd.trip4u.configuration.MailConfiguration
import com.nfd.trip4u.entity.mailing.Email
import com.nfd.trip4u.entity.mailing.Template
import com.nfd.trip4u.service.mailing.MailingService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 27 May 2016
 * Time: 14:54
 */

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(Application::class, MailConfiguration::class))
class TestSendMail {

    val TEMPLATE_NAME = "test-email"
    val TEST_KEY = "message"
    val SUBJECT = "trip4u: test message"
    val TEST_VALUE = "hello in trip4u"
    val SENDER = "night.fury.devs@gmail.com"
    val RECIPIENT = "kuchumova.mary@gmail.com"

    var parameters = HashMap<String, String>()

    @Autowired
    lateinit var mailingService: MailingService

    fun getSingleUserRecipients(): List<String>{
        var recipients = ArrayList<String>()
        recipients.add(RECIPIENT)
        return recipients
    }

    @Test
    fun testSendPlainTextMessage(){
        val email = Email(SENDER, getSingleUserRecipients(), SUBJECT, TEST_VALUE, null)
        mailingService.sendMessage(email)
    }

    @Test
    fun testSendHtmlMessage() {
        parameters.put(TEST_KEY, TEST_VALUE)
        val template = Template(TEMPLATE_NAME, parameters)
        val email = Email(SENDER, getSingleUserRecipients(), SUBJECT, null, template)
        mailingService.sendMessage(email)
    }

}