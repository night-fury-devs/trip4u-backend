package com.nfd.trip4u.service

/**
 * Author: Alexey Kleschikov
 * Date: 26 Jul 2016
 * Time: 06:46
 */

fun userNotFoundMsg(username: String): String {
    return "User $username doesn't exist."
}

fun placeNotFoundMsg(name: String): String {
    return "Place $name doesn't exist."
}

fun placeByIdNotFoundMsg(id: String): String {
    return "Place with id=$id doesn't exist."
}