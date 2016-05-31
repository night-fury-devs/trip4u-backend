package com.nfd.trip4u.entity.mongo

import org.springframework.data.mongodb.core.mapping.Field

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 17:57
 */

data class NotificationPreferences(
        var comments: List<NotificationLevel>?,
        var likes: List<NotificationLevel>?,
        var news: List<NotificationLevel>?,

        @Field(value = "route_usage")
        var routeUsage: List<NotificationLevel>?
        )