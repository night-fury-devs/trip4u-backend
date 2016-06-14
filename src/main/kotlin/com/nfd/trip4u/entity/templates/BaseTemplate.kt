package com.nfd.trip4u.entity.templates

import org.springframework.stereotype.Component

/**
 * Author: Mary Kuchumova
 * Date: 12 June 2016
 * Time: 14:28
 */

@Component
open class BaseTemplate {
    private lateinit var websiteLink: String
    private lateinit var contactLink: String
    private lateinit var username: String
    private lateinit var logoImageLink: String

    constructor(){}

    constructor(websiteLink: String, contactLink: String, username: String,
                logoImageLink: String) {
        this.websiteLink = websiteLink
        this.contactLink = contactLink
        this.username = username
        this.logoImageLink = logoImageLink
    }
}