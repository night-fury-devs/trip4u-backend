package com.nfd.trip4u.entity.domain

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 17:51
 */

@Document(collection = "routes.entries")
class RouteEntry() : IdentifiableEntity() {
    @Field(value = "place_id")
    lateinit var placeId: String

    @Field(value = "route_id")
    lateinit var routeId: String

    @Field(value = "prev_entry")
    var previousEntry: String? = null

    @Field(value = "next_entry")
    var nextEntry: String? = null

    constructor(placeId: String, routeId: String) : this() {
        this.placeId = placeId
        this.routeId = routeId
    }

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is RouteEntry) return false
        if (!super.equals(other)) return false

        if (placeId != other.placeId) return false
        if (routeId != other.routeId) return false
        if (previousEntry != other.previousEntry) return false
        if (nextEntry != other.nextEntry) return false

        return true
    }

    override fun hashCode(): Int{
        var result = super.hashCode()
        result = 31 * result + placeId.hashCode()
        result = 31 * result + routeId.hashCode()
        result = 31 * result + (previousEntry?.hashCode() ?: 0)
        result = 31 * result + (nextEntry?.hashCode() ?: 0)
        return result
    }
}