package com.nfd.trip4u.entity.templates

import java.io.Serializable

/**
 * Author: Mary Kuchumova
 * Date: 12 June 2016
 * Time: 14:29
 */

class CommentTemplate : NotificationTemplate, Serializable {

    lateinit var comment: Comment

    constructor() : super() {}

    constructor(websiteLink: String, contactLink: String, username: String,
                logoImageLink: String, imageLink: String, text: String, accessLink: String,
                comment: Comment)
                : super(websiteLink, contactLink, username, logoImageLink, imageLink,
                        text, accessLink) {
        this.comment = comment
    }
}