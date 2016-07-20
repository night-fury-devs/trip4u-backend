package com.nfd.trip4u.configuration

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.ehcache.EhCacheCacheManager
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

/**
 * Author: Mary Kuchumova
 * Date: 26 Jun 2016
 * Time: 00:04
 */

@Configuration
@EnableCaching
@ComponentScan(value = "com.nfd.trip4u.*")
open class EhcacheConfiguration{

    @Bean
    open fun cacheManager(): CacheManager {
        return EhCacheCacheManager(ehCacheCacheManager().`object`)
    }

    @Bean
    open fun ehCacheCacheManager(): EhCacheManagerFactoryBean {
        val cmfb = EhCacheManagerFactoryBean()
        cmfb.setConfigLocation(ClassPathResource("ehcache.xml"))
        cmfb.setShared(true)
        return cmfb
    }

}
