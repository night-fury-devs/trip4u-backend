package com.nfd.trip4u.entity.mailing

import com.nfd.trip4u.entity.templates.BaseTemplate

/**
 * Author: Mary Kuchumova
 * Date: 27 May 2016
 * Time: 17:38
 */

data class TemplateWrapper(
    var templateName: String,
    var template: BaseTemplate
)