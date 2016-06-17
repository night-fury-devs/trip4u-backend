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

    @Autowired
    @Qualifier("userRepository")
    lateinit var userRepository: UserRepository

    fun save(user: User): User {
        return userRepository.save(user)
    }

    fun delete(user: User) {
        userRepository.delete(user)
    }

    fun delete(id: Long) {
        userRepository.delete(id)
    }

    fun findAll(): Iterable<User> {
        return userRepository.findAll()
    }

    fun findAll(pageable: Pageable): Page<User> {
        return userRepository.findAll(pageable)
    }

    fun findById(id: Long): User {
        return userRepository.findOne(id)
    }

    fun findByUserName(userName: String): User {
        return userRepository.findUserByUserName(userName)
    }

    fun findByEmail(email: String): User {
        return userRepository.findUserByEmail(email)
    }

    fun findByUserNameAndPassword(userName: String, password: String): User {
        return userRepository.findUserByUserNameAndPassword(userName, password)
    }

    fun count(): Long {
        return userRepository.count()
    }

}