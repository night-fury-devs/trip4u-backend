package com.nfd.trip4u.dto.google

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Author: Alexey Kleschikov
 * Date: 07 Aug 2016
 * Time: 17:31
 */
class PhotoReference {

    @JsonProperty("photo_reference")
    lateinit var photoReference: String

    @JsonProperty("html_attributions")
    lateinit var htmlAttributions: Array<String>

    var height = 0
    var width = 0
}