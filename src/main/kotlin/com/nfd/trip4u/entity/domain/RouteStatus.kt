package com.nfd.trip4u.entity.domain

/**
 * Author: Mary Kuchumova
 * Date: 30 May 2016
 * Time: 17:19
 */

enum class RouteStatus(val value: String){
    VISITED("visited"),
    PLANNING_TO_VISIT("planning_to_visit"),
    IN_PROGRESS("in_progress")
}