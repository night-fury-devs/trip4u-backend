package com.nfd.trip4u.entity.mailing

/**
 * Author: Mary Kuchumova
 * Date: 27 May 2016
 * Time: 17:31
 */

data class Email(
    var sender: String = "",
    var recipients: List<String> = arrayListOf(),
    var subject: String = "",
    var messageBody: String? = "",
    var templateWrapper: TemplateWrapper? = null
)