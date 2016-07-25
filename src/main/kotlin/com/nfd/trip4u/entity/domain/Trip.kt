package com.nfd.trip4u.entity.domain

import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 06 July 2016
 * Time: 10:23
 */

data class Trip(
        var routeId: String,
        var status: RouteStatus,

        @Field(value = "start_date")
        var startDate: Date? = null,

        @Field(value = "end_date")
        val endDate: Date? = null
)