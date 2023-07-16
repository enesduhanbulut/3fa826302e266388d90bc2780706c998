package com.duhan.satelliteinfo.features.base.domain

import java.util.Date
import java.util.concurrent.TimeUnit

class RefreshControl(
    rate: Long = DEFAULT_REFRESH_RATE_MS,
    private var lastUpdateDate: Date? = null
) : ITimeLimitedResource {
    companion object {
        val ONE_TIME = 0L
        val NO_EXPIRATION = -1L
        val DEFAULT_REFRESH_RATE_MS = TimeUnit.MINUTES.toMillis(5)
    }

    interface Listener {
        suspend fun cleanup()
    }

    private val listeners: MutableList<Listener> = mutableListOf()
    private val children: MutableList<RefreshControl> = mutableListOf()
    private var isFetchedRemote = false

    // ITimeLimitedResource
    override var refreshRate: Long = rate
    override val lastUpdate: Date?
        get() = lastUpdateDate

    override suspend fun evict(cleanup: Boolean) {
        lastUpdateDate = null
        children.forEach { it.evict(cleanup) }
        if (cleanup) {
            listeners.forEach { it.cleanup() }
        }
    }

    // Public API
    fun createChild(): RefreshControl =
        RefreshControl(refreshRate, lastUpdateDate).also { children.add(it) }

    fun addListener(listener: Listener) {
        listeners.add(listener)
    }

    fun refresh() {
        lastUpdateDate = Date()
    }

    fun isExpired() = if (refreshRate != NO_EXPIRATION && refreshRate != ONE_TIME) {
        lastUpdateDate?.let { (Date().time - it.time) > refreshRate } ?: true
    } else if (refreshRate == ONE_TIME && !isFetchedRemote) {
        isFetchedRemote = true
        true
    } else {
        false
    }
}
