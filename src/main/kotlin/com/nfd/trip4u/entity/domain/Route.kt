package com.nfd.trip4u.entity.domain

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 17:16
 */

@Document(collection = "routes")
data class Route(
        var id: Long?,

        @Field(value = "user_id")
        var userId: Long,

        var name: String?,
        var blogs: List<Long>?,
        var tags: List<String>?,

        @Field(value = "added_to")
        var addedTo: List<UserCustomRoute>?,

        var description: String?,
        var comments: List<Comment>?,
        var rating: List<RatingEntry>?
        )