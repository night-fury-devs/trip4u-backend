package com.nfd.trip4u.entity.domain

/**
 * Author: Alexey Kleschikov
 * Date: 21 Jul 2016
 * Time: 07:14
 */
abstract class VerifiableEntity: CommentableEntity() {
    var status = VerificationStatus.NOT_VERIFIED

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is VerifiableEntity) return false
        if (!super.equals(other)) return false

        if (status != other.status) return false

        return true
    }

    override fun hashCode(): Int{
        var result = super.hashCode()
        result = 31 * result + status.hashCode()
        return result
    }


}