package com.nfd.trip4u.entity.domain

import org.springframework.data.mongodb.core.mapping.Field

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 17:57
 */

data class NotificationPreferences(
        var comments: List<NotificationLevel> = arrayListOf(),
        var likes: List<NotificationLevel> = arrayListOf(),
        var news: List<NotificationLevel> = arrayListOf(),

        @Field(value = "route_usage")
        var routeUsage: List<NotificationLevel> = arrayListOf()
        )