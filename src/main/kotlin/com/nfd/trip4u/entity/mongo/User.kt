package com.nfd.trip4u.entity.mongo

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 15:55
 */

@Document(collection = "users")
data class User(
        var id: Long,
        var userName: String,
        var email: String,
        var password: String,

        @Field(value = "home_cities")
        var homeCities: List<Long>?,

        @Field(value = "last_name")
        var lastName: String?,

        @Field(value = "first_name")
        var firstName: String?,

        @Field(value = "middle_name")
        var middleName: String?,

        var gender: Gender,

        @DateTimeFormat(pattern = "dd-MM-yyyy")
        var birthday: Date?,

        var role: List<Role>
)