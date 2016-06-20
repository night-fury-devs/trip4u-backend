package com.nfd.trip4u.controller.validation

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * Author: Mary Kuchumova
 * Date: 19 Jun 2016
 * Time: 20:02
 */

@Validated
open class ValidatedUser {

    @Size(min = 3, max = 30)
    @NotEmpty
    lateinit var userName: String

    @NotEmpty
    @Email
    lateinit var email: String

    @Pattern(regexp = "(?=^.{8,}$)(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z!@#$%^&*()]*$")
    lateinit var password: String

    @Size(max = 30)
    lateinit var lastName: String

    @Size(max = 30)
    lateinit var firstName: String
}
