package com.duhan.satelliteinfo.features.base.domain

import java.util.*

interface ITimeLimitedResource {
    var refreshRate: Long
    val lastUpdate: Date?

    suspend fun evict(cleanup: Boolean = false)
}
