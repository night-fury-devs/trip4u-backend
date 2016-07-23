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
}