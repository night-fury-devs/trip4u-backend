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
}