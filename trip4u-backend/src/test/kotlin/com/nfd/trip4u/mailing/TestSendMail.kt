package com.nfd.trip4u.mailing

import com.nfd.trip4u.Application
import com.nfd.trip4u.configuration.MailConfiguration
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
    val RECIPIENT = "kuchumova.mary@gmail.com"
    val RECIPIENT2 = "alex.vesy96@gmail.com"

    var parameters = HashMap<String, String>()

    @Autowired
    lateinit var mailingService: MailingService

    fun getMultiUserRecipients(): List<String>{
        var recipients = ArrayList<String>()
        recipients.add(RECIPIENT)
        recipients.add(RECIPIENT2)
        return recipients
    }

    fun getSingleUserRecipients(): List<String>{
        var recipients = ArrayList<String>()
        recipients.add(RECIPIENT)
        return recipients
    }

    @Test
    fun testSendPlainTextMessage(){
        mailingService.sendPlainTextEmail(SUBJECT, getSingleUserRecipients(), TEST_VALUE)
    }

    @Test
    fun testSendHtmlMessage() {
        parameters.put(TEST_KEY, TEST_VALUE)
        mailingService.sendTemplateEmail(SUBJECT, getMultiUserRecipients(), parameters, TEMPLATE_NAME)
    }

}