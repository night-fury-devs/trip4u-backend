package com.nfd.trip4u.dto.google

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

/**
 * Author: Alexey Kleschikov
 * Date: 07 Aug 2016
 * Time: 16:37
 */

class AddressComponent : Serializable {

    @JsonProperty("long_name")
    lateinit var longName: String

    @JsonProperty("short_name")
    lateinit var shortName: String
    lateinit var types: List<GooglePlaceType>
}