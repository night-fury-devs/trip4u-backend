package com.nfd.trip4u.repository.domain

import com.nfd.trip4u.entity.domain.Place
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Author: Alexey Kleschikov
 * Date: 29 Jul 2016
 * Time: 18:31
 */
interface PlaceRepository : MongoRepository<Place, String>, CommentableRepository<Place, String> {

    fun findByName(name: String): Place?
}