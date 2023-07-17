package com.duhan.satelliteinfo.features.core.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.repeatWithDelay(delayMillis: Long): Flow<T> = onEach {
    delay(delayMillis)
}