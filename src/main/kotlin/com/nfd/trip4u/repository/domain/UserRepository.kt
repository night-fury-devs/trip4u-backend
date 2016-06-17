package com.nfd.trip4u.repository.domain

import com.nfd.trip4u.entity.domain.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Author: Mary Kuchumova
 * Date: 17 June 2016
 * Time: 17:14
 */

@Repository
interface UserRepository : MongoRepository<User, Long> {

    fun findUserByLoginAndPassword(login: String, password: String): User

    fun findUserByLogin(login: String): User

    fun findUserByEmail(email: String): User

}