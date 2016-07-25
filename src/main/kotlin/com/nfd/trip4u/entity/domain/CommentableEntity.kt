package com.nfd.trip4u.entity.domain

/**
 * Author: Alexey Kleschikov
 * Date: 21 Jul 2016
 * Time: 07:15
 */
abstract class CommentableEntity: IdentifiableEntity() {
    var comments: List<Comment> = arrayListOf()

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is CommentableEntity) return false
        if (!super.equals(other)) return false

        if (comments != other.comments) return false

        return true
    }

    override fun hashCode(): Int{
        var result = super.hashCode()
        result = 31 * result + comments.hashCode()
        return result
    }
}