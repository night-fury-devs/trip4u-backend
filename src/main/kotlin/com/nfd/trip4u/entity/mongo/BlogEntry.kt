package com.nfd.trip4u.entity.mongo

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 17:09
 */


@Document(collection = "blog_entries")
data class BlogEntry(
        var id: Long,

        @Field(value = "blog_id")
        var blogId: Long,

        @Field(value = "route_entry_id")
        var routeEntryId: Long,

        @Field(value = "next_place")
        var nextPlace: Long,

        @Field(value = "previous_place")
        var previousPlace: Long,

        var date: Date,
        var text: String,

        @Field(value = "media_sources")
        var mediaSources: List<MediaSource>
        )