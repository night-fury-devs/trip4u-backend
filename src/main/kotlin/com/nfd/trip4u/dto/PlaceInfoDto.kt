package com.nfd.trip4u.dto

import com.nfd.trip4u.entity.domain.Geotag
import com.nfd.trip4u.entity.domain.PlaceType
import org.hibernate.validator.constraints.NotEmpty
import java.io.Serializable

/**
 * Author: Alexey Kleschikov
 * Date: 29 Jul 2016
 * Time: 06:44
 */
class PlaceInfoDto : Serializable {
    var id: String? = null

    @NotEmpty
    lateinit var name: String
    lateinit var placeType: PlaceType
    lateinit var geotag: Geotag
}