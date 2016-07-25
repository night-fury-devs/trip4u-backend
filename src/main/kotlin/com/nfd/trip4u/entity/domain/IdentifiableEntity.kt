package com.nfd.trip4u.entity.domain

/**
 * Author: Alexey Kleschikov
 * Date: 20 Jul 2016
 * Time: 20:17
 */
abstract class IdentifiableEntity {
    var id: String? = null

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is IdentifiableEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int{
        return id?.hashCode() ?: 0
    }
}