package com.nfd.trip4u.service.exception

/**
 * Author: Alexey Kleschikov
 * Date: 17 Jul 2016
 * Time: 13:01
 */
class EntityNotFoundException(
        override val message: String? = null,
        override val cause: Throwable? = null): Exception(message, cause) {
}