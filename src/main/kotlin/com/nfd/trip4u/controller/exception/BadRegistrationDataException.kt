package com.nfd.trip4u.controller.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Author: Mary Kuchumova
 * Date: 23 June 2016
 * Time: 15:28
 */

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "Can't create user with given credentials")
open class BadRegistrationDataException: RuntimeException() {

}