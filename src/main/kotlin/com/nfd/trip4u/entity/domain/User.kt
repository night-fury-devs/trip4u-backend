package com.nfd.trip4u.entity.domain

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.format.annotation.DateTimeFormat
import java.io.Serializable
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 15:55
 */

@Document(collection = "users")
class User() : IdentifiableEntity(), Serializable {

    lateinit var username: String
    lateinit var email: String
    lateinit var password: String
    var avatarUrl: String? = null

    @Field(value = "home_cities")
    var homeCities: List<String> = arrayListOf()

    var trips: List<Trip> = arrayListOf()

    @Field(value = "last_name")
    var lastName: String? = null

    @Field(value = "first_name")
    var firstName: String? = null

    @Field(value = "middle_name")
    var middleName: String? = null

    var gender: Gender = Gender.NOT_DEFINED

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    var birthday: Date? = null

    var role: List<Role> = arrayListOf(Role.USER)

    var activated: Boolean = false

    constructor(username: String, email: String, password: String): this() {
        this.username = username
        this.email = email
        this.password = password
    }
}
