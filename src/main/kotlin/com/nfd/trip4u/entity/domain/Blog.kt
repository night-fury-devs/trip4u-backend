package com.nfd.trip4u.entity.domain

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 16:59
 */

@Document(collection = "blogs")
class Blog() : VerifiableEntity() {

    lateinit var username: String

    @Field(value = "route_id")
    lateinit var routeId: String

    var title: String? = null
    var tags: List<String> = arrayListOf()
    var rating: List<Rating> = arrayListOf()

    @Field(value = "start_date")
    var startDate: Date? = null

    @Field(value = "end_date")
    var endDate: Date? = null

    constructor(username: String, routeId: String) : this() {
        this.username = username
        this.routeId = routeId
    }

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is Blog) return false
        if (!super.equals(other)) return false

        if (username != other.username) return false
        if (routeId != other.routeId) return false
        if (title != other.title) return false
        if (tags != other.tags) return false
        if (rating != other.rating) return false
        if (startDate != other.startDate) return false
        if (endDate != other.endDate) return false

        return true
    }

    override fun hashCode(): Int{
        var result = super.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + routeId.hashCode()
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + tags.hashCode()
        result = 31 * result + rating.hashCode()
        result = 31 * result + (startDate?.hashCode() ?: 0)
        result = 31 * result + (endDate?.hashCode() ?: 0)
        return result
    }
}