package com.nfd.trip4u.entity.domain

import org.springframework.data.mongodb.core.mapping.DBRef
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
    @DBRef
    var homeCities: MutableList<Place> = mutableListOf()

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

    constructor(username: String, email: String, password: String) : this() {
        this.username = username
        this.email = email
        this.password = password
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        if (!super.equals(other)) return false

        if (username != other.username) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (avatarUrl != other.avatarUrl) return false
        if (homeCities != other.homeCities) return false
        if (trips != other.trips) return false
        if (lastName != other.lastName) return false
        if (firstName != other.firstName) return false
        if (middleName != other.middleName) return false
        if (gender != other.gender) return false
        if (birthday != other.birthday) return false
        if (role != other.role) return false
        if (activated != other.activated) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + (avatarUrl?.hashCode() ?: 0)
        result = 31 * result + homeCities.hashCode()
        result = 31 * result + trips.hashCode()
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (firstName?.hashCode() ?: 0)
        result = 31 * result + (middleName?.hashCode() ?: 0)
        result = 31 * result + gender.hashCode()
        result = 31 * result + (birthday?.hashCode() ?: 0)
        result = 31 * result + role.hashCode()
        result = 31 * result + activated.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(username='$username', email='$email', password='$password', avatarUrl=$avatarUrl, " +
                "homeCities=$homeCities, trips=$trips, lastName=$lastName, firstName=$firstName, middleName=$middleName, " +
                "gender=$gender, birthday=$birthday, role=$role, activated=$activated)"
    }
}
