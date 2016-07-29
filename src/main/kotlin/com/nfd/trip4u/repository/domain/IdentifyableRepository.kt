package com.nfd.trip4u.repository.domain

import com.nfd.trip4u.entity.domain.IdentifiableEntity

/**
 * Author: Alexey Kleschikov
 * Date: 29 Jul 2016
 * Time: 18:54
 */
interface IdentifyableRepository<out T : IdentifiableEntity, in K> {

    fun findById(id: K): T?
}