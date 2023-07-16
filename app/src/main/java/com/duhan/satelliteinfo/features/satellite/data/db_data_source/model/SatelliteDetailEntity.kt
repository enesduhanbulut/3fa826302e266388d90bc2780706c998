package com.duhan.satelliteinfo.features.satellite.data.db_data_source.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.duhan.satelliteinfo.features.satellite.domain.model.SatelliteDetailDomainModel

@Entity(tableName = "satellite_detail")
data class SatelliteDetailEntity(
    @PrimaryKey
    val id: Int,
    val costPerLaunch: Int,
    val firstFlight: String,
    val height: Int,
    val mass: Int,
)

fun SatelliteDetailEntity.toDomainModel(): SatelliteDetailDomainModel {
    return SatelliteDetailDomainModel(
        id = this.id,
        costPerLaunch = this.costPerLaunch,
        firstFlight = this.firstFlight,
        height = this.height,
        mass = this.mass,
    )
}

