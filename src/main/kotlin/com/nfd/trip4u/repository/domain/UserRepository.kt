package com.nfd.trip4u.repository.domain

import com.nfd.trip4u.entity.domain.User
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Author: Mary Kuchumova
 * Date: 17 June 2016
 * Time: 17:14
 */

@Repository
@CacheConfig(cacheNames = arrayOf("registeredUsers"))
interface UserRepository : MongoRepository<User, String> {

    @Cacheable(value="registeredUsers", key="#a0")
    fun save(user: User): User

    fun findUserByUserNameAndPassword(userName: String, password: String): User?
    fun findUserByUserName(userName: String): User?
    fun findUserByEmail(email: String): User?
    fun findUserByUserNameOrEmail(userName: String, email: String): User?

}