package com.nfd.trip4u.dto

import com.nfd.trip4u.entity.domain.Location
import com.nfd.trip4u.entity.domain.PlaceType
import org.hibernate.validator.constraints.NotEmpty
import java.io.Serializable
import javax.validation.constraints.NotNull

/**
 * Author: Alexey Kleschikov
 * Date: 29 Jul 2016
 * Time: 06:44
 */
class PlaceInfoDto : Serializable {
    var id: String? = null

    @NotEmpty
    lateinit var name: String

    @NotNull
    lateinit var placeType: PlaceType

    @NotNull
    lateinit var location: Location

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is PlaceInfoDto) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (placeType != other.placeType) return false
        if (location != other.location) return false

        return true
    }

    override fun hashCode(): Int{
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + placeType.hashCode()
        result = 31 * result + location.hashCode()
        return result
    }


}