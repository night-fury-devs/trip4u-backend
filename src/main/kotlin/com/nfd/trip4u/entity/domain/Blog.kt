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
}