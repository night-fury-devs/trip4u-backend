package com.nfd.trip4u.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component

/**
 * Author: Alexey Kleschikov
 * Date: 19 Jun 2016
 * Time: 15:31
 */
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix="spring.security.cors")
open class CorsProperties {

    var origins: Array<String> = Array(0, { x -> "" })
}