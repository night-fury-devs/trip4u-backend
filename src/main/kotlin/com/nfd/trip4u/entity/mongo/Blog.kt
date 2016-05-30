package com.nfd.trip4u.entity.mongo

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 16:59
 */

@Document(collection = "blogs")
data class Blog(
        var id: Long,
        var title: String,

        @Field(value = "user_id")
        var userId: Long,

        @Field(value = "start_date")
        var startDate: Date,

        @Field(value = "end_date")
        var endDate: Date,

        var tags: List<String>,
        var status: VerificationStatus,
        var rating: List<RatingEntry>,

        @Field(value = "route_id")
        var routeId: Long,

        var comments: List<Comment>
        )