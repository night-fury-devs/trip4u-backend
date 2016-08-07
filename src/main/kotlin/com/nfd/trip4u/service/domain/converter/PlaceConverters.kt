package com.nfd.trip4u.service.domain.converter

import com.nfd.trip4u.dto.PlaceInfoDto
import com.nfd.trip4u.dto.google.GooglePlaceDto
import com.nfd.trip4u.dto.google.GooglePlaceType
import com.nfd.trip4u.entity.domain.Place
import com.nfd.trip4u.entity.domain.PlaceType

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
    placeInfo.location = this.location

    return placeInfo
}

fun List<Place>.toPlaceInfoList(): List<PlaceInfoDto> {
    val result: MutableList<PlaceInfoDto> = mutableListOf()

    this.forEach { result.add(it.toPlaceInfoDto()) }

    return result.toList()
}

fun GooglePlaceDto.toPlace(): Place {
    val type = this.type()
    val place = Place(this.name, this.location, type)

    place.id = this.placeId
    //TODO: use this.utcOffset in Place
    //TODO: convert this.photos to MediaSource list

    return place
}

fun GooglePlaceDto.type(): PlaceType {
    val cityTypes = arrayListOf(GooglePlaceType.LOCALITY, GooglePlaceType.ADMINISTRATIVE_AREA_LEVEL_3)
    val countryTypes = arrayListOf(GooglePlaceType.COUNTRY)

    if (this.types.intersect(cityTypes).size > 0) return PlaceType.CITY
    if (this.types.intersect(countryTypes).size > 0) return PlaceType.COUNTRY
    return PlaceType.PLACE
}
