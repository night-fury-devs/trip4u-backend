package com.nfd.trip4u.service.ehcache

import net.sf.ehcache.event.CacheEventListener
import net.sf.ehcache.event.CacheEventListenerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * Author: Mary Kuchumova
 * Date: 03 Jul 2016
 * Time: 18:48
 */

@Service
open class EhcacheListenerFactory : CacheEventListenerFactory() {

    @Autowired
    private lateinit var registeredUserCacheListener: RegisteredUserCacheListener

    override fun createCacheEventListener(p0: Properties?): CacheEventListener? {
        return RegisteredUserCacheListener()
    }

}
