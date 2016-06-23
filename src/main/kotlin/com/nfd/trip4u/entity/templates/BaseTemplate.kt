package com.nfd.trip4u.entity.templates

import java.io.Serializable

/**
 * Author: Mary Kuchumova
 * Date: 12 June 2016
 * Time: 14:28
 */

open class BaseTemplate: Serializable {
    lateinit var websiteLink: String
    lateinit var contactLink: String
    lateinit var username: String
    lateinit var logoImageLink: String

    constructor(){}

    constructor(websiteLink: String, contactLink: String, username: String,
                logoImageLink: String) {
        this.websiteLink = websiteLink
        this.contactLink = contactLink
        this.username = username
        this.logoImageLink = logoImageLink
    }
}