package com.nfd.trip4u.entity.mongo

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 17:04
 */

@Document(collection = "notifications")
data class Notification(
        var id: Long,

        @Field(value = "user_id")
        var userId: Long,

        var status: NotificationStatus,
        var date: Date,
        var text: String?,

        @Field(value = "attached_picture")
        var attachedPicture: String?,

        var link: String?
        )