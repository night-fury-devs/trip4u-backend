package com.nfd.trip4u.service.domain

import com.nfd.trip4u.dto.RegistrationDataDto
import com.nfd.trip4u.dto.UserDto
import com.nfd.trip4u.entity.domain.User
import com.nfd.trip4u.repository.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * Author: Mary Kuchumova
 * Date: 17 June 2016
 * Time: 17:45
 */

@Service
open class UserService {

    @Autowired
    @Qualifier("userRepository")
    lateinit var userRepository: UserRepository

    fun save(user: User): User {
        return userRepository.save(user)
    }

    fun delete(user: User) {
        userRepository.delete(user)
    }

    fun delete(id: String) {
        userRepository.delete(id)
    }

    fun findAll(): Iterable<User> {
        return userRepository.findAll()
    }

    fun findById(id: String?): User? {
        return userRepository.findOne(id)
    }

    fun findByUsername(username: String): User? {
        return userRepository.findUserByUsername(username)
    }

    fun findUserInfo(username: String): UserDto? {
        val user = userRepository.findUserByUsername(username) ?: return null
        val userDto = UserDto()

        userDto.username = user.username
        userDto.email = user.email
        userDto.firstName = user.firstName
        userDto.lastName = user.lastName
        userDto.birthday = user.birthday
        userDto.middleName = user.middleName
        userDto.gender = user.gender

        return userDto
    }

    fun findByEmail(email: String): User? {
        return userRepository.findUserByEmail(email)
    }

    fun findByUsernameAndPassword(username: String, password: String): User? {
        return userRepository.findUserByUsernameAndPassword(username, password)
    }

    fun count(): Long {
        return userRepository.count()
    }

    fun exists(registrationDataDto: RegistrationDataDto): Boolean {
        return userRepository.findUserByUsernameOrEmail(registrationDataDto.username, registrationDataDto.email) != null
    }

    fun updateUserInfo(username: String, userDto: UserDto) {
        val user = userRepository.findUserByUsername(username) ?: throw UsernameNotFoundException("") //TODO: Change
        // exception

        user.birthday = userDto.birthday
        user.email = userDto.email
        user.firstName = userDto.firstName
        user.lastName = userDto.lastName
        user.middleName = userDto.middleName
        user.gender = userDto.gender

        userRepository.save(user)
    }

    fun updateUserAvatar(username: String, avatarUrl: String?) {
        val user = userRepository.findUserByUsername(username) ?: throw UsernameNotFoundException("") //TODO: Change exception

        user.avatarUrl = avatarUrl
        userRepository.save(user)
    }

}