package com.nfd.trip4u.entity.domain

import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 16:48
 */

data class Comment(
        var user: Long,
        var text: String?,
        var date: Date,
        var likes: List<Long>?,
        var status: VerificationStatus
        )