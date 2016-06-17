package com.nfd.trip4u.entity.domain

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 17:53
 */

@Document(collection = "user_preferences")
data class UserPreferences(
        var id: Long,
        var locale: String,

        @Field(value = "user_id")
        var userId: Long,

        var notifications: NotificationPreferences?
        )