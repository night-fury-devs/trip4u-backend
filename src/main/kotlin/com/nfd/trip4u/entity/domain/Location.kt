package com.nfd.trip4u.entity.domain

import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 16:40
 */

data class Location(
        @Field(value = "lat")
        var latitude: Double,

        @Field(value = "lon")
        var longitude: Double
) : Serializable