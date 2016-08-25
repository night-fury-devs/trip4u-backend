package com.nfd.trip4u.dto.google

import com.fasterxml.jackson.annotation.JsonProperty
import com.nfd.trip4u.entity.domain.Location
import java.io.Serializable

/**
 * Author: Alexey Kleschikov
 * Date: 06 Aug 2016
 * Time: 19:26
 */
class GooglePlaceDto: Serializable {

    @JsonProperty("place_id")
    lateinit var placeId: String

    @JsonProperty("address_components")
    lateinit var addressComponents: List<AddressComponent>

    lateinit var location: Location
    lateinit var name: String
    lateinit var photos: List<PhotoReference>
    lateinit var types: List<GooglePlaceType>

    @JsonProperty("utc_offset")
    var utcOffset = 0
}