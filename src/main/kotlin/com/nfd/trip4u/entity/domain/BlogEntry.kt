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

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is BlogEntry) return false
        if (!super.equals(other)) return false

        if (blogId != other.blogId) return false
        if (routeEntryId != other.routeEntryId) return false
        if (nextPlace != other.nextPlace) return false
        if (previousPlace != other.previousPlace) return false
        if (date != other.date) return false
        if (text != other.text) return false
        if (mediaSources != other.mediaSources) return false

        return true
    }

    override fun hashCode(): Int{
        var result = super.hashCode()
        result = 31 * result + blogId.hashCode()
        result = 31 * result + routeEntryId.hashCode()
        result = 31 * result + (nextPlace?.hashCode() ?: 0)
        result = 31 * result + (previousPlace?.hashCode() ?: 0)
        result = 31 * result + (date?.hashCode() ?: 0)
        result = 31 * result + (text?.hashCode() ?: 0)
        result = 31 * result + mediaSources.hashCode()
        return result
    }
}