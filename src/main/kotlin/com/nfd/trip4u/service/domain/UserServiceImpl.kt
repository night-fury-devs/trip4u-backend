package com.nfd.trip4u.service.domain

import com.nfd.trip4u.entity.domain.User
import com.nfd.trip4u.repository.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.awt.print.Pageable

/**
 * Author: Mary Kuchumova
 * Date: 17 June 2016
 * Time: 17:45
 */

@Service
open class UserServiceImpl {

//    @Autowired
//    @Qualifier("userRepository")
//    lateinit var userRepository: UserRepository

    fun save(user: User): User {
        throw UnsupportedOperationException()
    }

    fun delete(user: User) {
        throw UnsupportedOperationException()
    }

    fun findAll(): Iterable<User> {
        throw UnsupportedOperationException()
    }

    fun findAll(pageable: Pageable): Page<User> {
        throw UnsupportedOperationException()
    }

    fun findById(id: Long): User {
        throw UnsupportedOperationException()
    }

    fun findByUserName(userName: String): User {
        throw UnsupportedOperationException()
    }

    fun findByEmail(email: String): User {
        throw UnsupportedOperationException()
    }

    fun findByUserNameAndPassword(userName: String, password: String): User {
        throw UnsupportedOperationException()
    }

    fun count(): Long {
        throw UnsupportedOperationException()
    }

}