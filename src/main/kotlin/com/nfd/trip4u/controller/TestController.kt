package com.nfd.trip4u.controller

import com.nfd.trip4u.service.domain.TestService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Author: Alexey Kleschikov
 * Date: 25 May 2016
 * Time: 20:15
 */

@RestController
open class TestController {

    private val logger = LogFactory.getLog(this.javaClass)

    @Autowired
    private lateinit var testService: TestService

    @RequestMapping("/")
    fun testMapping(): String {
        logger.debug("TEST CONTROLLER '/'")
        return testService.test()
    }
}
