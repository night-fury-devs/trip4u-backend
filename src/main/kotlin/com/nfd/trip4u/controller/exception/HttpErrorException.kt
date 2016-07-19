package com.nfd.trip4u.controller.exception

/**
 * Author: Alexey Kleschikov
 * Date: 19 Jul 2016
 * Time: 20:57
 */
abstract class HttpErrorException(override val message: String?,
                                  override val cause: Throwable?): RuntimeException(message, cause) {
}