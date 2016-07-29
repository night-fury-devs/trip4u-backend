package com.nfd.trip4u.repository.domain

import com.nfd.trip4u.entity.domain.CommentableEntity

/**
 * Author: Alexey Kleschikov
 * Date: 29 Jul 2016
 * Time: 18:56
 */
interface CommentableRepository<out T : CommentableEntity, in K> : IdentifyableRepository<T, K> {

}