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
}