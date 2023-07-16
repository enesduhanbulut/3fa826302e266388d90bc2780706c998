package com.duhan.satelliteinfo.features.satellite.domain.model

import com.duhan.satelliteinfo.features.satellite.data.db_data_source.model.SatelliteDetailEntity

data class SatelliteDetailDomainModel(
    val id: Int,
    val costPerLaunch: Int,
    val firstFlight: String,
    val height: Int,
    val mass: Int,
)

fun SatelliteDetailDomainModel.toEntity(): SatelliteDetailEntity {
    return SatelliteDetailEntity(
        id = this.id,
        costPerLaunch = this.costPerLaunch,
        firstFlight = this.firstFlight,
        height = this.height,
        mass = this.mass,
    )
}
