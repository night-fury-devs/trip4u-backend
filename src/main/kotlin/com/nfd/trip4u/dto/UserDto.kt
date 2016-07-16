package com.nfd.trip4u.dto

import com.nfd.trip4u.entity.domain.Gender
import java.util.*

/**
 * Author: Alexey Kleschikov
 * Date: 15 Jul 2016
 * Time: 07:14
 */

class UserDto {
    lateinit var userName: String
    lateinit var email: String
    var lastName: String? = null
    var firstName: String? = null
    var middleName: String? = null
    var gender: Gender = Gender.NOT_DEFINED
    var birthday: Date? = null
}