package com.nfd.trip4u.repository.domain

import com.nfd.trip4u.entity.domain.VerifiableEntity
import com.nfd.trip4u.entity.domain.VerificationStatus

/**
 * Author: Alexey Kleschikov
 * Date: 29 Jul 2016
 * Time: 18:59
 */
interface VerifyableRepository<out T : VerifiableEntity, in K> : CommentableRepository<T, K> {

    fun findByStatus(status: VerificationStatus): T?
}