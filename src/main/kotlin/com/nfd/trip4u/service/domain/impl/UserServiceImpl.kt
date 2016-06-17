package com.nfd.trip4u.service.domain.impl

import com.nfd.trip4u.entity.domain.User
import com.nfd.trip4u.repository.domain.UserRepository
import com.nfd.trip4u.service.domain.UserService
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
open class UserServiceImpl : UserService {

//    @Autowired
//    @Qualifier("userRepository")
//    lateinit var userRepository: UserRepository

    override fun save(user: User): User {
        throw UnsupportedOperationException()
    }

    override fun delete(user: User) {
        throw UnsupportedOperationException()
    }

    override fun findAll(): Iterable<User> {
        throw UnsupportedOperationException()
    }

    override fun findAll(pageable: Pageable): Page<User> {
        throw UnsupportedOperationException()
    }

    override fun findById(id: Long): User {
        throw UnsupportedOperationException()
    }

    override fun findByUserName(userName: String): List<User> {
        throw UnsupportedOperationException()
    }

    override fun findByEmail(email: String): List<User> {
        throw UnsupportedOperationException()
    }

    override fun findByUserNameAndPassword(userName: String, password: String): List<User> {
        throw UnsupportedOperationException()
    }

    override fun count(): Long {
        throw UnsupportedOperationException()
    }

}