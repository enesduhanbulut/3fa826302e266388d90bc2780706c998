package com.duhan.satelliteinfo.features.detail.domain

import com.duhan.satelliteinfo.features.satellite.domain.SatelliteRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GetPositions(private val satelliteRepository: SatelliteRepository) {
    fun invoke(id: Int) = satelliteRepository.getSatellitePosition(id)
        .map { domainModels -> domainModels?.map?.map { it.first.toString() + ", " + it.second.toString() } }
        .map { Result.success(it) }
        .catch { emit(Result.failure(it)) }

}