package com.nfd.trip4u.controller.util

import org.springframework.validation.BindingResult

/**
 * Author: Alexey Kleschikov
 * Date: 20 Jul 2016
 * Time: 07:49
 */

fun BindingResult.summary(): String {
    var result = ""

    this.allErrors.forEach { result += it.defaultMessage + "\n" }

    return result
}