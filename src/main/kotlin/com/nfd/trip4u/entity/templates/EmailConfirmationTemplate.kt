package com.nfd.trip4u.entity.templates

import org.springframework.stereotype.Component

/**
 * Author: Mary Kuchumova
 * Date: 12 June 2016
 * Time: 14:29
 */

@Component
open class EmailConfirmationTemplate : BaseTemplate {

    private lateinit var confirmationLink: String

    constructor() : super() { }

    constructor(websiteLink: String, contactLink: String, username: String,
                confirmationLink: String, logoImageLink: String)
                : super(websiteLink, contactLink, username, logoImageLink) {
        this.confirmationLink = confirmationLink
    }
}