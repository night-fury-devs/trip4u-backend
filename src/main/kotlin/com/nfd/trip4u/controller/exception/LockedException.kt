package com.nfd.trip4u.controller.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Author: Alexey Kleschikov
 * Date: 19 Jul 2016
 * Time: 21:14
 */
@ResponseStatus(value = HttpStatus.LOCKED)
open class LockedException(
        override var message: String? = null,
        override var cause: Throwable? = null) : HttpErrorException(message, cause) {
}