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
class Place() : CommentableEntity() {
    lateinit var name: String

    @Field(value = "type")
    lateinit var placeType: PlaceType

    @Field(value = "parent_place")
    var parentPlace: String? = null

    var geotag: Geotag? = null
    var rating: List<Rating> = arrayListOf()
    var description: String? = null
    var tags: List<String> = arrayListOf()

    @Field(value = "last_edited")
    var lastEdited: Date = Date()

    @Field(value = "media_sources")
    var mediaSources: List<MediaSource> = arrayListOf()

    @Field(value = "native_users")
    var nativeUsers: List<String> = arrayListOf()

    @Field(value = "planning_to_visit_users")
    var planningToVisitUsers: List<String> = arrayListOf()

    @Field(value = "visited_users")
    var visitedUsers: List<String> = arrayListOf()

    constructor(name: String, placeType: PlaceType) : this() {
        this.name = name
        this.placeType = placeType
    }
}