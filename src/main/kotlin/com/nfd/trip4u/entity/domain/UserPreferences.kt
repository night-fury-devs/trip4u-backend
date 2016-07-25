package com.nfd.trip4u.entity.domain

import org.springframework.data.mongodb.core.mapping.Document

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 17:53
 */

@Document(collection = "users.preferences")
class UserPreferences() : IdentifiableEntity() {
    lateinit var username: String
    var locale: String? = "en_GB"
    var notifications: NotificationPreferences = NotificationPreferences()

    constructor(username: String) : this() {
        this.username = username
    }

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is UserPreferences) return false
        if (!super.equals(other)) return false

        if (username != other.username) return false
        if (locale != other.locale) return false
        if (notifications != other.notifications) return false

        return true
    }

    override fun hashCode(): Int{
        var result = super.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + (locale?.hashCode() ?: 0)
        result = 31 * result + notifications.hashCode()
        return result
    }
}