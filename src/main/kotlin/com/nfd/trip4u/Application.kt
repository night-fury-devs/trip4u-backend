package com.nfd.trip4u

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching

/**
 * Author: Alexey Kleschikov
 * Date: 22 May 2016
 * Time: 23:16
 */

@EnableCaching
@SpringBootApplication
open class Application {

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args);
}