package com.duhan.satelliteinfo.features.detail.domain

import com.duhan.satelliteinfo.features.core.domain.repeatWithDelay
import com.duhan.satelliteinfo.features.satellite.domain.SatelliteRepository
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map

class GetPositions(private val satelliteRepository: SatelliteRepository) {
    fun invoke(id: Int, delay: Long = 3000L) = satelliteRepository.getSatellitePosition(id)
        .map { domainModels -> domainModels?.map?.map { it.first.toString() + ", " + it.second.toString() } }
        .flatMapConcat {
            it?.asFlow() ?: emptyList<String>().asFlow()
        }
        .repeatWithDelay(delay)

}