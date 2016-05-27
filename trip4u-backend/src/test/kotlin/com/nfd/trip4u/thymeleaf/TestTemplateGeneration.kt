package com.nfd.trip4u.thymeleaf

import com.nfd.trip4u.Application
import com.nfd.trip4u.configuration.ThymeleafConfiguration
import com.nfd.trip4u.service.thymeleaf.TemplateProducerService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
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

    var parameters = HashMap<String, String>()

    @Autowired
    lateinit var templateProducer: TemplateProducerService


    @Test
    fun testPassEmptyParameterMapToTemplate() {
        parameters.clear()
        var templateResult = templateProducer.produceTemplate(parameters, EXISTING_TEMPLATE_NAME)
        Assert.assertEquals(templateResult?.indexOf(TEST_VALUE), -1)
    }

    @Test
    fun testNotExistingTemplate() {
        parameters.clear()
        var templateResult = templateProducer.produceTemplate(parameters, NOT_EXISTING_TEMPLATE_NAME)
        Assert.assertNull(templateResult)
    }

    @Test
    fun testExistingTemplateGeneration(){
        parameters.put(TEST_KEY, TEST_VALUE)
        var templateResult = templateProducer.produceTemplate(parameters, EXISTING_TEMPLATE_NAME)
        Assert.assertFalse(templateResult?.indexOf(TEST_VALUE)!!.equals(-1))
    }
}
