package com.nfd.trip4u.controller.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Author: Mary Kuchumova
 * Date: 23 June 2016
 * Time: 15:28
 */

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
open class BadRequestException(message: String? = null, cause: Throwable? = null) : RuntimeException(message, cause) {

}