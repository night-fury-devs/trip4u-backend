package com.nfd.trip4u.entity.domain

/**
 * Author: Alexey Kleschikov
 * Date: 21 Jul 2016
 * Time: 07:14
 */
abstract class VerifiableEntity: CommentableEntity() {
    var status = VerificationStatus.NOT_VERIFIED
}