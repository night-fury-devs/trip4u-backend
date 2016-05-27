package com.nfd.trip4u.entity.mailing

/**
 * Author: Mary Kuchumova
 * Date: 27 Май 2016
 * Time: 17:38
 */

data class Template(
    var templateName: String,
    var parameters: Map<String, String>
)