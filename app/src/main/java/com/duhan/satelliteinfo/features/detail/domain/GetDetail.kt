package com.duhan.satelliteinfo.features.detail.domain

import com.duhan.satelliteinfo.features.detail.presentation.SatelliteDetailUIModel
import com.duhan.satelliteinfo.features.satellite.domain.SatelliteRepository
import com.duhan.satelliteinfo.features.satellite.domain.model.toUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GetDetail(private val satelliteRepository: SatelliteRepository) {
    fun invoke(id: Int): Flow<Result<SatelliteDetailUIModel?>> {
        return satelliteRepository.getSatelliteDetail(id)
            .map { domainModels -> domainModels?.toUIModel() }
            .map { Result.success(it) }
            .catch { emit(Result.failure(it)) }
    }

}