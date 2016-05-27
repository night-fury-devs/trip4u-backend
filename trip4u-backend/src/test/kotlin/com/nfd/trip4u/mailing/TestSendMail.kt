package com.nfd.trip4u.mailing

import com.nfd.trip4u.Application
import com.nfd.trip4u.configuration.MailConfiguration
import com.nfd.trip4u.service.mailing.MailingService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * Author: Mary Kuchumova
 * Date: 27 May 2016
 * Time: 14:54
 */

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(Application::class, MailConfiguration::class))
class TestSendMail {

    @Autowired
    lateinit var mailingService: MailingService

    @Test
    fun testMailSending(){
        mailingService.sendPlainTextEmail("test", "kuchumova.mary@gmail.com", "test message")
    }

}