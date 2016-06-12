package com.nfd.trip4u.entity.templates

/**
 * Author: Mary Kuchumova
 * Date: 12 June 2016
 * Time: 14:29
 */

open class CommentTemplate : NotificationTemplate {

    private var comment: Comment
    get() = comment
    set(value) {
        comment = value
    }

    constructor(websiteLink: String, contactLink: String, username: String,
                logoImageLink: String, imageLink: String, text: String, accessLink: String,
                comment: Comment)
                : super(websiteLink, contactLink, username, logoImageLink, imageLink,
                        text, accessLink) {
        this.comment = comment
    }
}