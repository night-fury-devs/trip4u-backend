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
    lateinit var geotag: Geotag

    @Field(value = "type")
    lateinit var placeType: PlaceType

    @Field(value = "parent_place")
    var parentPlace: String? = null
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

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is Place) return false
        if (!super.equals(other)) return false

        if (name != other.name) return false
        if (placeType != other.placeType) return false
        if (parentPlace != other.parentPlace) return false
        if (geotag != other.geotag) return false
        if (rating != other.rating) return false
        if (description != other.description) return false
        if (tags != other.tags) return false
        if (lastEdited != other.lastEdited) return false
        if (mediaSources != other.mediaSources) return false
        if (nativeUsers != other.nativeUsers) return false
        if (planningToVisitUsers != other.planningToVisitUsers) return false
        if (visitedUsers != other.visitedUsers) return false

        return true
    }

    override fun hashCode(): Int{
        var result = super.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + placeType.hashCode()
        result = 31 * result + (parentPlace?.hashCode() ?: 0)
        result = 31 * result + geotag.hashCode()
        result = 31 * result + rating.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + tags.hashCode()
        result = 31 * result + lastEdited.hashCode()
        result = 31 * result + mediaSources.hashCode()
        result = 31 * result + nativeUsers.hashCode()
        result = 31 * result + planningToVisitUsers.hashCode()
        result = 31 * result + visitedUsers.hashCode()
        return result
    }
}