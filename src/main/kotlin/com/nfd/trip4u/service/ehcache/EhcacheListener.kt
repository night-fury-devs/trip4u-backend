package com.nfd.trip4u.service.ehcache

import com.nfd.trip4u.entity.domain.User
import com.nfd.trip4u.service.domain.UserService
import net.sf.ehcache.Ehcache
import net.sf.ehcache.Element
import net.sf.ehcache.event.CacheEventListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Author: Mary Kuchumova
 * Date: 03 Jul 2016
 * Time: 18:48
 */

@Service
open class EhcacheListener : CacheEventListener {

    @Autowired
    private lateinit var userService: UserService

    override fun dispose() {
    }

    override fun notifyRemoveAll(p0: Ehcache?) {
    }

    override fun notifyElementRemoved(p0: Ehcache?, p1: Element?) {
    }

    override fun notifyElementPut(p0: Ehcache?, p1: Element?) {
        println("added")
    }

    override fun clone(): Any {
        return this
    }

    override fun notifyElementExpired(ehcache: Ehcache?, element: Element?) {
        val user = element!!.objectValue as User;
        if (!user.activated){
            userService.delete(user)
        }
        println("expired")
    }

    override fun notifyElementUpdated(p0: Ehcache?, p1: Element?) {
    }

    override fun notifyElementEvicted(p0: Ehcache?, p1: Element?) {
    }

}
