package com.nfd.trip4u.entity.templates


/**
 * Author: Mary Kuchumova
 * Date: 12 June 2016
 * Time: 14:28
 */

open class NotificationTemplate : BaseTemplate {

    private var imageLink: String
    get() = imageLink
    set(value) {
        imageLink = value
    }

    private var text: String
    get() = text
    set(value) {
        text = value
    }

    private var accessLink: String
    get() = accessLink
    set(value) {
        accessLink = value
    }

    constructor(websiteLink: String, contactLink: String, username: String,
                logoImageLink: String, imageLink: String, text: String, accessLink: String)
                : super(websiteLink, contactLink, username, logoImageLink) {
        this.imageLink = imageLink
        this.text = text
        this.accessLink = accessLink
    }
}