package com.nfd.trip4u.entity.templates

/**
 * Author: Mary Kuchumova
 * Date: 12 June 2016
 * Time: 14:28
 */


open class BaseTemplate {
    private var websiteLink: String
//    get() = websiteLink
//    set(value) {
//        websiteLink = value
//    }

    private var contactLink: String
//    get() = contactLink
//    set(value) {
//        contactLink = value
//    }

    private var username: String
//    get() = username
//    set(value) {
//        username = value
//    }

    private var logoImageLink: String
//    get() = logoImageLink
//    set(value) {
//        logoImageLink = value
//    }

    constructor(websiteLink: String, contactLink: String, username: String,
                logoImageLink: String) {
        this.websiteLink = websiteLink
        this.contactLink = contactLink
        this.username = username
        this.logoImageLink = logoImageLink
    }
}