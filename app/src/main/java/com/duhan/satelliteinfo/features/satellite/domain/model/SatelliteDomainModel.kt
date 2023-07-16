package com.duhan.satelliteinfo.features.satellite.domain.model

import com.duhan.satelliteinfo.features.satellite.data.db_data_source.model.SatelliteItemEntity

data class SatelliteDomainModel(
    val id: Int,
    val name: String,
    val isActive: Boolean,
)
fun SatelliteDomainModel.toEntity(): SatelliteItemEntity {
    return SatelliteItemEntity(
        id = this.id,
        name = this.name,
        isActive = this.isActive,
    )
}