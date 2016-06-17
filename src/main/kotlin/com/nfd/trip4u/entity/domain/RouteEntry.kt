package com.nfd.trip4u.entity.domain

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 17:51
 */

@Document(collection = "route_entries")
data class RouteEntry(
        var id: Long,

        @Field(value = "place_id")
        var placeId: Long,

        @Field(value = "route_id")
        var routeId: Long,

        @Field(value = "prev_entry")
        var previousEntry: Long?,

        @Field(value = "next_entry")
        var nextEntry: Long?
        )