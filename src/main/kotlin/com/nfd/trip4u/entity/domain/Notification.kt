package com.nfd.trip4u.entity.domain

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 17:04
 */

@Document(collection = "notifications")
class Notification() : IdentifiableEntity() {
    lateinit var username: String
    var status: NotificationStatus = NotificationStatus.UNWATCHED
    var date: Date = Date()
    var text: String? = null
    var link: String? = null

    @Field(value = "attached_picture")
    var attachedPicture: String? = null

    constructor(username: String, date: Date = Date()) : this() {
        this.username = username
        this.date = date
    }
}