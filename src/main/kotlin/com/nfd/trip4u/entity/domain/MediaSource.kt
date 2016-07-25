package com.nfd.trip4u.entity.domain

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 30 Май 2016
 * Time: 16:46
 */

@Document(collection = "media")
class MediaSource() : VerifiableEntity() {

    lateinit var author: String
    lateinit var link: String
    lateinit var type: MediaType
    var geotag: Geotag? = null
    var likes: List<String> = arrayListOf()

    @Field(value = "date_added")
    var dateAdded: Date = Date()

    constructor(author: String, link: String, type: MediaType) : this() {
        this.author = author
        this.link = link
        this.type = type
    }

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is MediaSource) return false
        if (!super.equals(other)) return false

        if (author != other.author) return false
        if (link != other.link) return false
        if (type != other.type) return false
        if (geotag != other.geotag) return false
        if (likes != other.likes) return false
        if (dateAdded != other.dateAdded) return false

        return true
    }

    override fun hashCode(): Int{
        var result = super.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + link.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + (geotag?.hashCode() ?: 0)
        result = 31 * result + likes.hashCode()
        result = 31 * result + dateAdded.hashCode()
        return result
    }


}