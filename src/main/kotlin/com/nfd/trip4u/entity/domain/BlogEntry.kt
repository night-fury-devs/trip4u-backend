package com.nfd.trip4u.entity.domain

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 17:09
 */


@Document(collection = "blogs.entries")
class BlogEntry() : IdentifiableEntity() {
    @Field(value = "blog_id")
    lateinit var blogId: String

    @Field(value = "route_entry_id")
    lateinit var routeEntryId: String

    @Field(value = "next_place")
    var nextPlace: String? = null

    @Field(value = "previous_place")
    var previousPlace: String? = null

    var date: Date? = null
    var text: String? = null

    @Field(value = "media_sources")
    var mediaSources: List<MediaSource> = arrayListOf()

    constructor(blogId: String, routeEntryId: String) : this() {
        this.blogId = blogId
        this.routeEntryId = routeEntryId
    }
}