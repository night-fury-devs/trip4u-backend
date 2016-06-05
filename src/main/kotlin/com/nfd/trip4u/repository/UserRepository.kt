package com.nfd.trip4u.repository

import com.nfd.trip4u.entity.mongo.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

/**
 * Author: Alexey Kleschikov
 * Date: 05 Jun 2016
 * Time: 22:37
 */

@Repository
interface UserRepository : MongoRepository<User, Long> {
    @Query(value = "{email: 'b'}")
    fun findFirst(): User
}