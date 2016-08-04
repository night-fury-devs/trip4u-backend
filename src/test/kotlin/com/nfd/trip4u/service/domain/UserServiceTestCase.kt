package com.nfd.trip4u.service.domain

import com.nfd.trip4u.AbstractTestCase
import com.nfd.trip4u.dto.RegistrationDataDto
import com.nfd.trip4u.entity.domain.Geotag
import com.nfd.trip4u.entity.domain.Place
import com.nfd.trip4u.entity.domain.PlaceType
import com.nfd.trip4u.entity.domain.User
import com.nfd.trip4u.repository.domain.PlaceRepository
import com.nfd.trip4u.repository.domain.UserRepository
import com.nfd.trip4u.service.domain.converter.toPlaceInfoList
import com.nfd.trip4u.service.domain.converter.toUserDto
import com.nfd.trip4u.service.exception.EntityNotFoundException
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Author: Mary Kuchumova
 * Date: 17 Jun 2016
 * Time: 22:56
 */
@Component
open class UserServiceTestCase : AbstractTestCase() {

    private val NOT_EXISTING_USERNAME = "user***not///exist--"

    private lateinit var user: User
    private lateinit var place: Place
    private lateinit var registrationData: RegistrationDataDto

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var placeRepository: PlaceRepository

    @Before
    fun populateTestData() {
        registrationData = RegistrationDataDto()
        registrationData.email = "q@a"
        registrationData.username = "qqq"
        registrationData.password = "qqq"

        place = Place("Test", Geotag(24.0, 24.0), PlaceType.CITY)
        place = placeRepository.save(place)
        assertNotNull(place.id)

        user = User("test user", "test@mail.com", "passwrd")
        user.homeCities.add(place)
        user = userRepository.save(user)

        assertNotNull(user.id)
    }

    @Test
    fun findUserByUsername(){
        val foundUser = userService.findByUsername(user.username)
        assertEquals(foundUser, user)
    }

    @Test
    fun findUserByEmail(){
        val foundUser = userService.findByEmail(user.email)
        assertEquals(foundUser, user)
    }

    @Test
    fun findUserByCredentials(){
        val foundUser = userService.findByUsernameAndPassword(user.username, user.password)
        assertEquals(foundUser, user)
    }

    @Test
    fun getUsersCount(){
        val usersCount = userService.count()
        assertNotEquals(usersCount, 0)
    }

    @Test
    fun findUserInfo() {
        val userInfo = userService.findUserInfo(user.username)

        assertEquals("User info should be the same.", user.toUserDto(), userInfo)
    }

    @Test(expected = EntityNotFoundException::class)
    fun findUserInfoNotExistingUser() {
        userService.findUserInfo(NOT_EXISTING_USERNAME)
    }

    @Test
    fun existsUsername() {
        val data = registrationData
        data.username = user.username

        val result = userService.exists(data)

        assertTrue("User with username ${user.username} should exists.", result)
    }

    @Test
    fun existsEmail() {
        val data = registrationData
        data.email = user.email

        val result = userService.exists(data)

        assertTrue("User with email ${user.email} should exists.", result)
    }

    @Test
    fun existsUsernameAndEmail() {
        val data = registrationData
        data.email = user.email
        data.username = user.username

        val result = userService.exists(data)

        assertTrue("User with username ${user.username} and email ${user.email} should exists.", result)
    }

    @Test
    fun existsFalse() {
        val result = userService.exists(registrationData)

        assertFalse("User with username ${registrationData.username} and email ${registrationData.email} shouldn't " +
                "exist.", result)
    }

    @Test
    fun updateUserInfo() {
        val userDto = user.toUserDto()
        userDto.firstName = "firstName"

        userService.updateUserInfo(userDto.username, userDto)

        val user = userService.findUserInfo(userDto.username)

        assertEquals("User info should be the same.", userDto, user)
    }

    @Test(expected = EntityNotFoundException::class)
    fun updateUserInfoNotExistingUser() {
        userService.updateUserInfo(NOT_EXISTING_USERNAME, user.toUserDto())
    }

    @Test
    fun updateUserAvatar() {
        userService.updateUserAvatar(user.username, "some url")

        val user = userService.findByUsername(user.username)

        assertEquals("Avatar url should be updated.", "some url", user?.avatarUrl)
    }

    @Test
    fun updateUserAvatarWithNull() {
        userService.updateUserAvatar(user.username, null)

        val user = userService.findByUsername(user.username)

        assertNull("User avatar should be null.", user?.avatarUrl)
    }

    @Test(expected = EntityNotFoundException::class)
    fun updateUserAvatarNotExistingUser() {
        userService.updateUserAvatar(NOT_EXISTING_USERNAME, "")
    }

    @Test
    fun findHomeCities() {
        val cities = userService.findHomeCities(user.username)

        assertEquals("Cities should be the same.", user.homeCities.toPlaceInfoList(), cities)
    }

    @Test(expected = EntityNotFoundException::class)
    fun findHomeCitiesNotExistingUser() {
        val cities = userService.findHomeCities(NOT_EXISTING_USERNAME)
    }

    @After
    fun removeTestData() {
        userRepository.deleteAll()
        placeRepository.deleteAll()
    }
}