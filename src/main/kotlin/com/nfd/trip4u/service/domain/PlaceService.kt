package com.nfd.trip4u.service.domain

import com.nfd.trip4u.dto.PlaceInfoDto
import com.nfd.trip4u.repository.domain.PlaceRepository
import com.nfd.trip4u.service.domain.converter.toPlaceInfoDto
import com.nfd.trip4u.service.exception.EntityNotFoundException
import com.nfd.trip4u.service.placeByIdNotFoundMsg
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Author: Alexey Kleschikov
 * Date: 29 Jul 2016
 * Time: 18:34
 */

@Service
open class PlaceService {

    @Autowired
    private lateinit var placeRepository: PlaceRepository

    fun getPlaceInfo(id: String): PlaceInfoDto {
        val place = placeRepository.findById(id) ?:
                throw EntityNotFoundException(placeByIdNotFoundMsg(id))

        return place.toPlaceInfoDto()
    }
}