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
}