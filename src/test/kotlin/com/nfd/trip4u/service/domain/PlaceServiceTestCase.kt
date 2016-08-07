package com.nfd.trip4u.service.domain

import com.nfd.trip4u.AbstractTestCase
import com.nfd.trip4u.entity.domain.Location
import com.nfd.trip4u.entity.domain.Place
import com.nfd.trip4u.entity.domain.PlaceType
import com.nfd.trip4u.repository.domain.PlaceRepository
import com.nfd.trip4u.service.domain.converter.toPlaceInfoDto
import com.nfd.trip4u.service.exception.EntityNotFoundException
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Author: Alexey Kleschikov
 * Date: 31 Jul 2016
 * Time: 19:42
 */
@Component
open class PlaceServiceTestCase: AbstractTestCase() {

    private val INCORRECT_ID = "..."
    private lateinit var place: Place

    @Autowired
    private lateinit var placeService: PlaceService

    @Autowired
    private lateinit var placeRepository: PlaceRepository

    @Before
    fun populateTestData() {
        place = Place("Test City", Location(25.4, 36.54), PlaceType.CITY)
        placeRepository.save(place)

        assertNotNull("Saved place should have id.", place.id)
    }

    @Test
    fun getPlaceInfo() {
        val placeInfo = placeService.getPlaceInfo(place.id ?: "")

        assertEquals("Place should have the same info.", place.toPlaceInfoDto(), placeInfo)
    }

    @Test(expected = EntityNotFoundException::class)
    fun getPlaceInfoNotExistingPlace() {
        placeService.getPlaceInfo(INCORRECT_ID)
    }

    @After
    fun removeTestData() {
        placeRepository.deleteAll()
    }

}