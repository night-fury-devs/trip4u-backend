package com.nfd.trip4u.service.domain.converter

import com.nfd.trip4u.dto.UserDto
import com.nfd.trip4u.entity.domain.User

/**
 * Author: Alexey Kleschikov
 * Date: 25 Jul 2016
 * Time: 21:39
 */

fun User.toUserDto() : UserDto {
    val userDto = UserDto()

    userDto.username = this.username
    userDto.email = this.email
    userDto.gender = this.gender
    userDto.birthday = this.birthday
    userDto.firstName = this.firstName
    userDto.middleName = this.middleName
    userDto.lastName = this.lastName

    return userDto
}

fun UserDto.toUser() : User {
    val user = User()

    user.username = this.username
    user.email = this.email
    user.gender = this.gender
    user.birthday = this.birthday
    user.firstName = this.firstName
    user.middleName = this.middleName
    user.lastName = this.lastName

    return user
}

fun User.updateWith(userDto: UserDto) {
    this.email = userDto.email
    this.gender = userDto.gender
    this.birthday = userDto.birthday
    this.firstName = userDto.firstName
    this.middleName = userDto.middleName
    this.lastName = userDto.middleName
}