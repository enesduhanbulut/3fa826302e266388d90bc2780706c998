package com.duhan.satelliteinfo.features.home.domain

import com.duhan.satelliteinfo.features.satellite.domain.SatelliteRepository
import com.duhan.satelliteinfo.features.satellite.domain.model.toUIModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GetSatellites(
    private val repository: SatelliteRepository
) {
    operator fun invoke() = repository.getSatelliteList()
        .map { domainModels -> domainModels?.map { it.toUIModel() } }
        .map { Result.success(it) }
        .catch { emit(Result.failure(it)) }
}