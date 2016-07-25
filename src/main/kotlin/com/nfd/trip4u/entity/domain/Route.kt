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

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is Route) return false
        if (!super.equals(other)) return false

        if (username != other.username) return false
        if (name != other.name) return false
        if (blogs != other.blogs) return false
        if (tags != other.tags) return false
        if (description != other.description) return false
        if (rating != other.rating) return false

        return true
    }

    override fun hashCode(): Int{
        var result = super.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + blogs.hashCode()
        result = 31 * result + tags.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + rating.hashCode()
        return result
    }
}