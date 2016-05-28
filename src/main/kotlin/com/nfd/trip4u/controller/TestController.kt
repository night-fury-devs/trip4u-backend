package com.nfd.trip4u.controller

import org.apache.commons.logging.LogFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Author: Alexey Kleschikov
 * Date: 25 May 2016
 * Time: 20:15
 */

@RestController
open class TestController {
    val logger = LogFactory.getLog(this.javaClass)

    @RequestMapping("/")
    fun testMapping(): String {
        logger.debug("TEST CONTROLLER '/'")
        return "Your Heroku deployment is working."
    }
}
