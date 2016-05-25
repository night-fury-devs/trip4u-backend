package com.nfd.trip4u.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Author: Alexey Kleschikov
 * Date: 25 May 2016
 * Time: 20:15
 */

@RestController
open class TestController {

    @RequestMapping("/")
    fun testMapping() = "Your Heroku deployment is working."
}
