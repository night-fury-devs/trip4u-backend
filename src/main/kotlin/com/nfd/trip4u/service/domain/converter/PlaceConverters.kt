package com.nfd.trip4u.service.domain.converter

import com.nfd.trip4u.dto.PlaceInfoDto
import com.nfd.trip4u.entity.domain.Place

/**
 * Author: Alexey Kleschikov
 * Date: 29 Jul 2016
 * Time: 18:50
 */

fun Place.toPlaceInfoDto(): PlaceInfoDto {
    val placeInfo = PlaceInfoDto()

    placeInfo.id = this.id
    placeInfo.name = this.name
    placeInfo.placeType = this.placeType
    placeInfo.geotag = this.geotag

    return placeInfo
}