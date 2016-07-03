package com.nfd.trip4u.service.ehcache

import net.sf.ehcache.Ehcache
import net.sf.ehcache.Element
import net.sf.ehcache.event.CacheEventListener
import org.springframework.stereotype.Service

/**
 * Author: Mary Kuchumova
 * Date: 03 Jul 2016
 * Time: 18:48
 */

@Service
open class EhcacheListener : CacheEventListener {

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

    override fun notifyElementExpired(p0: Ehcache?, p1: Element?) {
        println("expired")
    }

    override fun notifyElementUpdated(p0: Ehcache?, p1: Element?) {
    }

    override fun notifyElementEvicted(p0: Ehcache?, p1: Element?) {
    }

}
