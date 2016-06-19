package com.nfd.trip4u.entity.domain

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 16:35
 */

@Document(collection = "places")
data class Place(
        var id: Long?,
        var name: String?,

        @Field(value = "parent_place")
        var parentPlace: Long?,

        @Field(value = "type")
        var placeType: PlaceType,

        var geotag: Geotag?,
        var rating: List<RatingEntry>?,
        var description: String?,
        var tags: List<String>?,

        @Field(value = "last_edited")
        var lastEdited: Date?,

        @Field(value = "media_sources")
        var mediaSources: List<MediaSource>?,

        @Field(value = "native_users")
        var nativeUsers: List<Long>?,

        @Field(value = "planning_to_visit_users")
        var planningToVisitUsers: List<Long>?,

        @Field(value = "visited_users")
        var visitedUsers: List<Long>?,

        var comments: List<Comment>?
        )