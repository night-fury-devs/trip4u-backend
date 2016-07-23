package com.nfd.trip4u.entity.domain

import org.springframework.data.mongodb.core.mapping.Document

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 17:16
 */

@Document(collection = "routes")
class Route() : CommentableEntity() {
    lateinit var username: String

    var name: String? = null
    var blogs: List<Long> = arrayListOf()
    var tags: List<String> = arrayListOf()
    var description: String? = null
    var rating: List<Rating> = arrayListOf()

    constructor(username: String) : this() {
        this.username = username
    }
}