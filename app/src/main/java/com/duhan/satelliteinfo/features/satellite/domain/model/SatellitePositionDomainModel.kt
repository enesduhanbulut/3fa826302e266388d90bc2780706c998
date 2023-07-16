package com.duhan.satelliteinfo.features.satellite.domain.model

import com.duhan.satelliteinfo.features.satellite.data.db_data_source.model.SatellitePositionEntity

data class SatellitePositionDomainModel(
    val id: Int,
    val map: List<Pair<Double, Double>>
)
fun SatellitePositionDomainModel.toEntity(): SatellitePositionEntity {
    return SatellitePositionEntity(
        id = id,
        posX = map[0].first,
        posY = map[0].second
    )
}