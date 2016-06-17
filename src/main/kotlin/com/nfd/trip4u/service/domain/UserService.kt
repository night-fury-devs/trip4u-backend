package com.nfd.trip4u.service.domain

import com.nfd.trip4u.entity.domain.User
import org.springframework.data.domain.Page
import java.awt.print.Pageable

/**
 * Author: Mary Kuchumova
 * Date: 17 June 2016
 * Time: 17:44
 */
interface UserService {

    fun save(user: User): User
    fun delete(user: User)
    fun findAll(): Iterable<User>
    fun findAll(pageable: Pageable): Page<User>
    fun findById(id: Long): User
    fun findByUserName(userName: String): List<User>
    fun findByEmail(email: String): List<User>
    fun findByUserNameAndPassword(userName: String, password: String): List<User>
    fun count(): Long

}