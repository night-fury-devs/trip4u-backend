package com.nfd.trip4u.service.domain

import com.nfd.trip4u.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Author: Alexey Kleschikov
 * Date: 05 Jun 2016
 * Time: 22:48
 */

@Service
open class TestService {

    @Autowired
    private lateinit var userRepository: UserRepository

    fun test(): String {
        return userRepository.findFirst().userName
    }
}