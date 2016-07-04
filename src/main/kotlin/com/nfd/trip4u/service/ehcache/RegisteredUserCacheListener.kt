package com.nfd.trip4u.service.ehcache

import com.nfd.trip4u.entity.domain.User
import com.nfd.trip4u.service.domain.UserService
import net.sf.ehcache.Ehcache
import net.sf.ehcache.Element
import net.sf.ehcache.event.CacheEventListener
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Author: Mary Kuchumova
 * Date: 04 Jule 2016
 * Time: 15:50
 */

@Service
open class RegisteredUserCacheListener: CacheEventListener, Cloneable {

    private val log = LogFactory.getLog(this.javaClass)

    @Autowired
    private lateinit var userService: UserService

    private fun user(element: Element?): User? {
        return element!!.objectValue as User
    }

    override fun notifyElementEvicted(ehcache: Ehcache?, element: Element?) {
        log.info("Ehcache element evicted: " + user(element))
    }

    override fun notifyElementUpdated(ehcache: Ehcache?, element: Element?) {
        log.info("Ehcache element updated: " + user(element))
    }

    override fun notifyElementExpired(ehcache: Ehcache?, element: Element?) {
        val user = user(element)
        log.info("Ehcache element expired: " + user)
        if (!user!!.activated){
            userService.delete(user)
        }
    }

    override fun clone(): Any {
        return super.clone()
    }

    override fun notifyElementPut(ehcache: Ehcache?, element: Element?) {
        log.info("Ehcache element put: " + user(element))
    }

    override fun notifyElementRemoved(ehcache: Ehcache?, element: Element?) {
        log.info("Ehcache element removed: " + user(element))
    }

    override fun notifyRemoveAll(ehcache: Ehcache?) {
        log.info("All ehcache elements were removed")
    }

    override fun dispose() {

    }

}