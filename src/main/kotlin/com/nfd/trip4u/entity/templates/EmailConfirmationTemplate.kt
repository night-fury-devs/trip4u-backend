package com.nfd.trip4u.entity.templates

import java.io.Serializable

/**
 * Author: Mary Kuchumova
 * Date: 12 June 2016
 * Time: 14:29
 */

class EmailConfirmationTemplate : BaseTemplate, Serializable {

    lateinit var confirmationLink: String

    constructor() : super() { }

    constructor(websiteLink: String, contactLink: String, username: String,
                confirmationLink: String, logoImageLink: String)
                : super(websiteLink, contactLink, username, logoImageLink) {
        this.confirmationLink = confirmationLink
    }
}