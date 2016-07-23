package com.nfd.trip4u.entity.domain

/**
 * Author: Alexey Kleschikov
 * Date: 21 Jul 2016
 * Time: 07:15
 */
abstract class CommentableEntity: IdentifiableEntity() {
    var comments: List<Comment> = arrayListOf()
}