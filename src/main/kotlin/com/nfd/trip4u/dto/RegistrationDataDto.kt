package com.nfd.trip4u.dto

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.cache.annotation.Cacheable
import org.springframework.validation.annotation.Validated
import java.io.Serializable
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * Author: Mary Kuchumova
 * Date: 19 Jun 2016
 * Time: 20:02
 */

@Cacheable(value = "true")
@Validated
open class RegistrationDataDto : Serializable {

    @Size(min = 3, max = 30)
    @NotEmpty
    lateinit var userName: String

    @NotEmpty
    @Email
    lateinit var email: String

    @Pattern(regexp = "(?=^.{8,}$)(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z!@#$%^&*()]*$")
    lateinit var password: String

    @Size(max = 30)
    var lastName: String = ""

    @Size(max = 30)
    var firstName: String = ""
}
