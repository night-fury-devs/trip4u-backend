package com.nfd.trip4u.entity.mongo

import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 30 Май 2016
 * Time: 16:46
 */


data class MediaSource(
        var author: Long,

        @Field(value = "date_added")
        var dateAdded: Date,

        var link: String?,
        var status: VerificationStatus,
        var geotag: Geotag?,

        @Field(value = "type")
        var mediaType: MediaType,

        var likes: List<Long>?,

        var comments: List<Comment>?
        )