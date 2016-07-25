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

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is Notification) return false
        if (!super.equals(other)) return false

        if (username != other.username) return false
        if (status != other.status) return false
        if (date != other.date) return false
        if (text != other.text) return false
        if (link != other.link) return false
        if (attachedPicture != other.attachedPicture) return false

        return true
    }

    override fun hashCode(): Int{
        var result = super.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + (text?.hashCode() ?: 0)
        result = 31 * result + (link?.hashCode() ?: 0)
        result = 31 * result + (attachedPicture?.hashCode() ?: 0)
        return result
    }
}