package com.nfd.trip4u.entity.templates

/**
 * Author: Mary Kuchumova
 * Date: 12 June 2016
 * Time: 14:28
 */

open class NotificationTemplate : BaseTemplate {

    lateinit var imageLink: String
    lateinit var text: String
    lateinit  var accessLink: String

    constructor() : super() {}

    constructor(websiteLink: String, contactLink: String, username: String,
                logoImageLink: String, imageLink: String, text: String, accessLink: String)
                : super(websiteLink, contactLink, username, logoImageLink) {
        this.imageLink = imageLink
        this.text = text
        this.accessLink = accessLink
    }
}