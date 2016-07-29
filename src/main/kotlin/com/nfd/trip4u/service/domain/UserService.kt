package com.nfd.trip4u.service.domain

import com.nfd.trip4u.dto.PlaceInfoDto
import com.nfd.trip4u.dto.RegistrationDataDto
import com.nfd.trip4u.dto.UserDto
import com.nfd.trip4u.entity.domain.User
import com.nfd.trip4u.repository.domain.UserRepository
import com.nfd.trip4u.service.domain.converter.toUserDto
import com.nfd.trip4u.service.domain.converter.updateWith
import com.nfd.trip4u.service.exception.EntityNotFoundException
import com.nfd.trip4u.service.userNotFoundMsg
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
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

    @Autowired
    lateinit var placeService: PlaceService

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

    fun findUserInfo(username: String): UserDto {
        val user = userRepository.findUserByUsername(username) ?:
                throw EntityNotFoundException(userNotFoundMsg(username))

        return user.toUserDto()
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
        val user = userRepository.findUserByUsername(username) ?:
                throw EntityNotFoundException(userNotFoundMsg(username))

        user.updateWith(userDto)

        userRepository.save(user)
    }

    fun updateUserAvatar(username: String, avatarUrl: String?) {
        val user = userRepository.findUserByUsername(username) ?:
                throw EntityNotFoundException(userNotFoundMsg(username))

        user.avatarUrl = avatarUrl
        userRepository.save(user)
    }

    fun findHomeCities(username: String): List<PlaceInfoDto> {
        val user = userRepository.findUserByUsername(username) ?:
                throw EntityNotFoundException(userNotFoundMsg(username))
        val cities: MutableList<PlaceInfoDto> = mutableListOf()

        user.homeCities.forEach {
            val place = placeService.getPlaceInfo(it)
            cities.add(place)
        }

        return cities
    }

}