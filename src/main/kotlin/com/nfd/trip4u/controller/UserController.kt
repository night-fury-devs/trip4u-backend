package com.nfd.trip4u.controller

import com.nfd.trip4u.controller.validation.ValidatedUser
import com.nfd.trip4u.entity.domain.Gender
import com.nfd.trip4u.entity.domain.Role
import com.nfd.trip4u.entity.domain.User
import com.nfd.trip4u.service.domain.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

/**
 * Author: Mary Kuchumova
 * Date: 19 Jun 2016
 * Time: 19:25
 */

@RestController
@RequestMapping(value = "/user")
open class UserController {

    @Autowired
    private lateinit var userService: UserService

    @RequestMapping(value = "/register", method = arrayOf(RequestMethod.POST))
    @ResponseStatus(value = HttpStatus.OK)
    open fun register(@Valid @RequestBody user: ValidatedUser,
                      bindingResult: BindingResult): HttpStatus {
        if (!bindingResult.hasErrors()) {
            if (userService.findByEmail(user.email) != null ||
                    userService.findByUserName(user.userName) != null) {
                return HttpStatus.BAD_REQUEST
            }
            val domainUserEntity = User(null, user.userName, user.email, user.password, null,
                    user.lastName, user.firstName, null, Gender.NOT_DEFINED, null,
                    ArrayList<Role>(Arrays.asList(Role.USER)))
            userService.save(domainUserEntity)
            //TODO: add sending verification email to registered user
            return HttpStatus.CREATED
        } else {
            return HttpStatus.BAD_REQUEST
        }
    }

    @RequestMapping(value = "/validateLogin", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    open fun validateLogin(login: String): Boolean {
        return userService.findByUserName(login) != null
    }

    @RequestMapping(value = "/validateEmail", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    open fun validateEmai(email: String): Boolean {
        return userService.findByEmail(email) != null
    }

}