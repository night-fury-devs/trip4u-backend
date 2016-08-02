package com.nfd.trip4u.dto

import com.nfd.trip4u.entity.domain.Gender
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty
import java.io.Serializable
import java.util.*
import javax.validation.constraints.NotNull

/**
 * Author: Alexey Kleschikov
 * Date: 15 Jul 2016
 * Time: 07:14
 */

class UserDto : Serializable {

    @NotEmpty
    lateinit var username: String

    @NotEmpty
    @Email
    lateinit var email: String
    var lastName: String? = null
    var firstName: String? = null
    var middleName: String? = null

    @NotNull
    var gender: Gender = Gender.NOT_DEFINED
    var birthday: Date? = null

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is UserDto) return false

        if (username != other.username) return false
        if (email != other.email) return false
        if (lastName != other.lastName) return false
        if (firstName != other.firstName) return false
        if (middleName != other.middleName) return false
        if (gender != other.gender) return false
        if (birthday != other.birthday) return false

        return true
    }

    override fun hashCode(): Int{
        var result = username.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (firstName?.hashCode() ?: 0)
        result = 31 * result + (middleName?.hashCode() ?: 0)
        result = 31 * result + gender.hashCode()
        result = 31 * result + (birthday?.hashCode() ?: 0)
        return result
    }
}