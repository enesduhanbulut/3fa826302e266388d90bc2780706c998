package com.duhan.satelliteinfo.features.satellite.data.db_data_source.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.duhan.satelliteinfo.features.satellite.domain.model.SatellitePositionDomainModel

@Entity(tableName = "satellite_position")
data class SatellitePositionEntity(
    @PrimaryKey
    val id: Int = 0,
    val posX: Double = 0.0,
    val posY: Double = 0.0
)
fun SatellitePositionEntity.toDomainModel(): SatellitePositionDomainModel {
    return SatellitePositionDomainModel(
        id,
        map = listOf(posX to posY)
    )
}